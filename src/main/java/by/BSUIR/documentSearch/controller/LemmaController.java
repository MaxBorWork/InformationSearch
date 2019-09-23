package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
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
    private Map<String, Integer> lemmaCount = new HashMap<>();
    private List<Lemma> lemmas;
    private List<String> lemmasInDB;
    private List<String> lemmasForSave;
    private List<Option<String>> lexList;


    public LemmaController() {
        this.lemmaDao = new LemmaDao();
    }

    public Iterable<Info> parseText(String text) throws MyStemApplicationException {
        return JavaConversions.asJavaIterable(
                mystemAnalyzer
                        .analyze(Request.apply(text))
                        .info()
                        .toIterable());
    }

    public void processQueryInfo(Iterable<Info> result) {
        lexList = new ArrayList<>();
        iterateInfo(result);
    }

    public List<String> getLemmasList() {
        List<String> lemmasList = new ArrayList<String>();
        for (Option<String> lex : lexList) {
            lemmasList.add(lex.get());
        }
        return lemmasList;
    }

    public void processDocumentInfo(Iterable<Info> result) {
        lexList = new ArrayList<>();
        lemmasForSave = new ArrayList<>();
        lemmasInDB = lemmaDao.getLemmaNames();
        iterateInfo(result);
        for (Option<String> lex : lexList) {
            isLexInList(lex.get());
        }
        lemmaDao.saveLemmas(lemmasForSave);
    }

    private void iterateInfo(Iterable<Info> result) {
        for (final Info info : result) {
            checkLex(info.lex());
        }
    }

    private void checkLex(Option<String> lex) {
        if (lex.nonEmpty()) {
            lexList.add(lex);
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
        if (!lemmasInDB.contains(lex)) {
            lemmasForSave.add(lex);
        }
    }

    private void saveLexToList(String lex) {
        lemmaCount.put(lex, 1);
    }

    private void increaseLexCount(String lex) {
        lemmaCount.put(lex, lemmaCount.get(lex)+1);
    }


    public void setLemmas() {
        this.lemmas = lemmaDao.getLemmas();
    }

    public Map<String, Integer> getLemmaCount() {
        return lemmaCount;
    }
}
