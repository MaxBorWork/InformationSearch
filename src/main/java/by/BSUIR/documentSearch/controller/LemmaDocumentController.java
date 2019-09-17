package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.LemmaDao;
import by.BSUIR.documentSearch.dao.LemmaDocumentDao;
import by.BSUIR.documentSearch.model.LemmaDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemmaDocumentController {
    private LemmaDao lemmaDao;
    private LemmaDocumentDao lemmaDocumentDao;

    public LemmaDocumentController() {
        this.lemmaDao = new LemmaDao();
        this.lemmaDocumentDao = new LemmaDocumentDao();
    }

    public void saveLemmaDocument(int documentID, Map<String, Integer> lemmaCount) {
        List<LemmaDocument> lemmaDocuments = new ArrayList<>();
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
}
