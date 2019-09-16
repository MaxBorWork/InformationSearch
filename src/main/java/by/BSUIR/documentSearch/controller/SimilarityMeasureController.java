package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.model.Document;

import java.util.Vector;

public class SimilarityMeasureController {
    private Vector searchQueryVector;

    public SimilarityMeasureController(Vector<Double> searchQueryVector) {
        this.searchQueryVector = searchQueryVector;
    }

    public Integer similarityMeasureFor(Vector<Double> documentVector) {
        Double ENsearchQuary = getEN(searchQueryVector);
        Double ENdocument = getEN(documentVector);
        Double scalar = getScalarProduct(searchQueryVector, documentVector);

        return (int) (scalar / (ENsearchQuary * ENdocument));
    }

    private Double getEN(Vector<Double> vector) {
        Double amount = 0.0;

        for (int i = 0; i < vector.size(); i++) {
            amount += (vector.get(i) * vector.get(i));
        }

        return Math.sqrt(amount);
    }

    private Double getScalarProduct(Vector<Double> search, Vector<Double> document) {
        Double amount = 0.0;

        for (int i = 0; i < search.size(); i++) {
            amount += (search.get(i) * document.get(i));
        }

        return amount;
    }
}