package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.dao.LemmaDao;
import by.BSUIR.documentSearch.dao.LemmaDocumentDao;
import by.BSUIR.documentSearch.model.Document;

import java.util.Map;
import java.util.Vector;

public class DocumentController {

    private DocumentDao documentDao;
    private int colOfDocumentsInBase = 0;
    private Document document;
    private LemmaDao lemmaDao;
    private LemmaDocumentDao lemmaDocumentDao;

    public DocumentController(Document document) {
        this.lemmaDao = new LemmaDao();
        this.lemmaDocumentDao = new LemmaDocumentDao();
        this.document = document;
    }

    public Vector<Double> createDocumentVector() {
        Vector<Double> vectorForDoc = new Vector<>();
        Vector<Double> numerator = new Vector<>();
        Double denominator = 0.0;
        Double amount = 0.0;
        int numberOfDocWithThisLem = 0;

        for (Map.Entry<String, Integer> lemmaCountEntity : document.getLemmCount().entrySet()) {
            int lemmaID = lemmaDao.getLemma(lemmaCountEntity.getKey()).getId();
            numberOfDocWithThisLem = lemmaDocumentDao.getDistinctDocumentsForLemma(lemmaID);
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

    private int numberOfOccurrencesLemInDoc(String lem) {
        //TODO получить число вхождений лемы в документ
        return 0;
    }

}
