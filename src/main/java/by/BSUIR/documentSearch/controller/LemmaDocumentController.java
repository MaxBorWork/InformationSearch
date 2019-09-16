package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.LemmaDocumentDao;

import java.util.Map;

public class LemmaDocumentController {

    private LemmaDocumentDao lemmaDocumentDao;

    public LemmaDocumentController() {
        this.lemmaDocumentDao = new LemmaDocumentDao();
    }

    public void updateLemmaDocumentTable(Map<String, Integer> lexCount) {
        for (Map.Entry<String, Integer> lexCountEntity : lexCount.entrySet()) {

        }
    }
}
