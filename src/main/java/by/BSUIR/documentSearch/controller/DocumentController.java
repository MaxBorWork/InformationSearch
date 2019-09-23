package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.dao.LemmaDao;
import by.BSUIR.documentSearch.dao.LemmaDocumentDao;
import by.BSUIR.documentSearch.model.Baze;
import by.BSUIR.documentSearch.model.Document;
import by.BSUIR.documentSearch.model.LemmaDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DocumentController {

    private DocumentDao documentDao;
    private int colOfDocumentsInBase = 0;
    private Document document;
    private LemmaDao lemmaDao;
    private LemmaDocumentDao lemmaDocumentDao;

    public DocumentController(Document document) {
        this.document = document;
        this.lemmaDao = new LemmaDao();
        this.lemmaDocumentDao = new LemmaDocumentDao();
        this.colOfDocumentsInBase = Baze.getNumberOfDocInBase();
    }

    public Vector<Double> createDocumentVector() {
        Vector<Double> vectorForDoc = new Vector<>();
        Vector<Double> numerator = new Vector<>();
        Double denominator = 0.0;
        Double amount = 0.0;
        int numberOfDocWithThisLem = 0;

        for (Map.Entry<String, Integer> lemmaCountEntity : document.getLemmCount().entrySet()) {
            int lemmaID = lemmaDao.getLemma(lemmaCountEntity.getKey()).getId();
            numberOfDocWithThisLem = lemmaDocumentDao.getColOfDocumentsForLemma(lemmaID);
            numerator.add(lemmaCountEntity.getValue() * Math.log(colOfDocumentsInBase / numberOfDocWithThisLem));
        }

        for (int i = 0; i < numerator.size(); i++) {
            amount += numerator.get(i) * numerator.get(i);
        }

        denominator = Math.sqrt(amount);

        for (int i = 0; i < numerator.size(); i++) {
            vectorForDoc.add(numerator.get(i) / denominator);
        }

        return vectorForDoc;
    }

    public List<String> getKeyWords() {
        return lemmaDocumentDao.getKeyLemmasFromDocument(document.getId());
    }


    public void calculateLemmasWeight() {
        List<LemmaDocument> lemmaDocuments = lemmaDocumentDao.getLemmaCountFromDocument(document.getId());
        for (LemmaDocument lemmaDocument : lemmaDocuments) {
            int lemmaID = lemmaDocument.getLemmaID();
            double weight = getLemmaWeight(document.getId(), lemmaID);

            lemmaDocument.setLemmaWeight(weight);
        }
        lemmaDocumentDao.setLemmaWeights(lemmaDocuments);
    }

    private double getLemmaWeight(int documentID, int lemmaID) {
        double idf = getLemmaInverseFrequency(lemmaID);
        double tf = calcLemmaFrequency(lemmaID, documentID);
        return tf * idf;
    }


    private double getLemmaInverseFrequency(int lemmaID) {
        int numberOfDocWithLemma = lemmaDocumentDao.getColOfDocumentsForLemma(lemmaID);
        int colOfDocumentsInBase = documentDao.getDocuments().size();
        return Math.log(colOfDocumentsInBase / numberOfDocWithLemma);
    }

    private double calcLemmaFrequency(int lemmaID, int documentID) {
        int lemmaCount = lemmaDocumentDao.getLemmaCountFromDocument(lemmaID, documentID);
        int wordsInDocument = documentDao.getDocumentColOfWords(documentID);
        return lemmaCount / wordsInDocument;
    }



}
