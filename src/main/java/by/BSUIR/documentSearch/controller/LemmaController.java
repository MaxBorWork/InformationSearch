package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.LemmaDao;
import by.BSUIR.documentSearch.dao.LemmaDocumentDao;
import by.BSUIR.documentSearch.model.Document;
import by.BSUIR.documentSearch.model.Lemma;
import by.BSUIR.documentSearch.model.LemmaDocument;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemmaController {

    private final static MyStem mystemAnalyzer =
            new Factory("-igd --eng-gr --format json --weight")
                    .newMyStem("3.0", Option.<File>empty()).get();

    private LemmaDao lemmaDao;
    private LemmaDocumentDao lemmaDocumentDao;
    private Map<String, Integer> lemmaCount = new HashMap<>();
    private List<Lemma> lemmas;
    private List<LemmaDocument> lemmaDocuments;

    public LemmaController() {
        lemmaDocuments = new ArrayList<>();
        this.lemmaDao = new LemmaDao();
        this.lemmaDocumentDao = new LemmaDocumentDao();
    }

    public void parseDocument(Document document) throws MyStemApplicationException {
        final Iterable<Info> result =
                JavaConversions.asJavaIterable(
                        mystemAnalyzer
                                .analyze(Request.apply(document.getText()))
                                .info()
                                .toIterable());
        iterateInfo(result);
        document.setLemmCount(lemmaCount);
        saveLemmaDocument(document.getId());
    }

    private void iterateInfo(Iterable<Info> result) {
        for (final Info info : result) {
            checkLex(info.lex());
        }
    }

    private void checkLex(Option<String> lex) {
        if (lex.nonEmpty()) {
            isLexInList(lex.get());
        }
    }

    private void isLexInList(String lex) {
        if (!lemmaCount.containsKey(lex)) {
            processLemmaForSave(lex);
        } else {
            increaseLexCount(lex);
        }
    }

    private void processLemmaForSave(String lex) {
        saveLexToList(lex);
        if (!isInDB(lex)) {
            saveLemma(lex);
        }
    }

    private void saveLexToList(String lex) {
        lemmaCount.put(lex, 1);
    }

    private boolean isInDB(String lex) {
        return lemmaDao.getLemma(lex) != null;
    }

    private void saveLemma(String lex) {
        Lemma lemma = new Lemma(lex, 1);
        lemmaDao.saveLemma(lemma);
    }

    private void increaseLexCount(String lex) {
        lemmaCount.put(lex, lemmaCount.get(lex)+1);
    }

    private void saveLemmaDocument(int documentID) {
        for (Map.Entry<String, Integer> lemmaCountEntity : lemmaCount.entrySet()) {
            LemmaDocument lemmaDocument = new LemmaDocument(
                    lemmaDao.getLemma(lemmaCountEntity.getKey()).getId(),
                    documentID,
                    lemmaCountEntity.getValue()
            );
            lemmaDocuments.add(lemmaDocument);
        }
        lemmaDocumentDao.saveLemmaDocuments(lemmaDocuments);
    }

    public List<Lemma> getLemmas() {
        return lemmas;
    }

    public void setLemmas() {
        this.lemmas = lemmaDao.getLemmas();
    }
}
