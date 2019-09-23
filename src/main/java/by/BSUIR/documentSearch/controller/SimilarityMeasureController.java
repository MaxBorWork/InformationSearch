package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.model.Document;

import java.util.List;
import java.util.Vector;

public class SimilarityMeasureController {
    private List searchQueryVector;

    public SimilarityMeasureController(List<Double> searchQueryVector) {
        this.searchQueryVector = searchQueryVector;
    }

    public Integer similarityMeasureFor(List<Double> documentVector) {
        Double ENsearchQuary = getEN(searchQueryVector);
        Double ENdocument = getEN(documentVector);
        Double scalar = getScalarProduct(searchQueryVector, documentVector);

        return (int) (scalar / (ENsearchQuary * ENdocument));
    }

    private Double getEN(List<Double> vector) {
        Double amount = 0.0;

        for (int i = 0; i < vector.size(); i++) {
            amount += (vector.get(i) * vector.get(i));
        }

        return Math.sqrt(amount);
    }

    private Double getScalarProduct(List<Double> search, List<Double> document) {
        Double amount = 0.0;

        for (int i = 0; i < search.size(); i++) {
            amount += (search.get(i) * document.get(i));
        }

        return amount;
    }
}