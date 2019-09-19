package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.dao.LemmaDao;
import by.BSUIR.documentSearch.dao.LemmaDocumentDao;
import by.BSUIR.documentSearch.model.Lemma;
import by.BSUIR.documentSearch.model.LemmaDocument;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VocabularyController {
    private Integer numberOfDocInBase;

    private List<Double> weightList;
    private List<Lemma> lemmaList; //список лемм
    private List<Vector<Document>> documentVector; //вектор документа

    private int documentID = 0;
    private LemmaDao lemmaDao;
    private DocumentDao documentDao;
    private LemmaDocumentDao lemmaDocumentDao;

    public VocabularyController(List<Lemma> lemmList, List<Vector<Document>> documentVector) {

        this.numberOfDocInBase = documentVector.size();
        this.lemmaList = lemmList;
        this.documentVector = documentVector;
        this.weightList = new ArrayList<Double>();

    }

    public VocabularyController(int documentID) {
        this.lemmaDao = new LemmaDao();
        this.documentDao = new DocumentDao();
        this.lemmaDocumentDao = new LemmaDocumentDao();

        this.documentID = documentID;
        this.lemmaList = lemmaDao.getLemmas();
    }

    void createVocabulary() {///ATTENTION  VOID
        for(int i = 0; i < lemmaList.size(); i++){
//            weightList.add(getNumberOfDocWithThisLemm(lemmList[i]), );
        }
    }

    void getNumberOfDocWithThisLemma(Lemma lemma){
        //TODO(Йо-хо-хо)
        System.out.println("Йо-хо-хо");
    }

    public void calculateLemmasWeight() {
        List<LemmaDocument> lemmaDocuments = lemmaDocumentDao.getLemmaCountFromDocument(documentID);
        for (LemmaDocument lemmaDocument : lemmaDocuments) {
            int lemmaID = lemmaDocument.getLemmaID();
            double weight = getLemmaWeight(documentID, lemmaID);

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

    public void setLemmaList(List<Lemma> lemmaList) {
        this.lemmaList = lemmaList;
    }
}
