package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.model.Document;

import java.util.*;

public class MainController {

    private SearchQueryController searchQueryController;
    private Vector searchQuery;
    private List<Document> documents;
    private List<String> documentsForResponse;
    private Map<Document, Integer> docToSimilarityMeasure;
    private LemmaController lemmaController;
    private DocumentDao documentDao;

    public MainController(String searchRequest, List<Document> docList) {
   //     this.lemmaController = new LemmaController();
        this.documents = docList;
        this.documentDao = new DocumentDao();
        this.searchQueryController = new SearchQueryController(searchRequest);
        this.documents = documentDao.getDocuments();
    }

    public List<String> getListForResponse() {
        docToSimilarityMeasure = new HashMap<Document, Integer>();

        for (Document doc : documents) {

           SimilarityMeasureController similarityMeasureController =  new SimilarityMeasureController(searchQueryController.getVectorOfSearchQuery(doc.getKeyWords()));
            docToSimilarityMeasure.put(doc, similarityMeasureController.similarityMeasureFor(doc.getDocumentVector()));
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
