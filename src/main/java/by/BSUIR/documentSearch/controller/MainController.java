package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.model.Document;

import javax.print.Doc;
import java.util.*;

public class MainController {

    private SearchQueryController searchQueryController;
    private Vector vectorSearchQuary;
    private List<Document> documentList;
    private List<String> documentsForResponse;
    private Map<Document, Integer> docToSimilarityMeasure;
    private SimilarityMeasureController similarityMeasureController;

    public MainController(String searchRequest) {

        this.searchQueryController = new SearchQueryController(searchRequest);
        this.similarityMeasureController = new SimilarityMeasureController(searchQueryController.getVectorOfSearchQuary());
        //TODO ЗДЕСЬ МНЕ НУЖЕН СПИСОК ДОКУМЕНТОВ тех которые у  теюя в структурке
        // this.documentList


    }

    public List<String> getListForResponse() {
        docToSimilarityMeasure = new HashMap<Document, Integer>();

        for (Document doc : documentList) {
            docToSimilarityMeasure.put(doc, similarityMeasureController.similarityMeasureFor(doc.createDocumentVector()));
        }

        List<Integer> docBySimilarityMeasure = new ArrayList<Integer>(docToSimilarityMeasure.values());
        Collections.sort(docBySimilarityMeasure);

        List<Document> docs = new ArrayList<Document>(docToSimilarityMeasure.keySet());
        documentsForResponse = new ArrayList<String>();

        if (docs.size() > 20) {
            for (int i = 0; i < 20; i++) {
                documentsForResponse.add(docs.get(i).getTitle());
            }

        } else {
            for (int i = 0; i < docs.size(); i++) {
                documentsForResponse.add(docs.get(i).getTitle());
            }

        }

        return documentsForResponse;
    }

}
