package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.model.Lemma;
import by.BSUIR.documentSearch.model.Search;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.model.Info;

import java.util.List;
import java.util.Vector;

public class SearchQueryController {
    private String searchRequest;
    private List<String> lemmas;
    private LemmaController lemmaController;

    public SearchQueryController(String searchRequest) {
        this.searchRequest = searchRequest;
        this.lemmaController = new LemmaController();
        this.lemmas = getLemmasList();
    }


    private Boolean include(String keyWord){
        for(int i = 0; i< lemmas.size(); i++){
            if(lemmas.get(i).equals(keyWord)) return true;

        }
        return false;
    }

    public Vector getVectorOfSearchQuery(List<String> documentKeyWords) {
        Vector<Integer> vectorOfZeroAndOneForQuerySearch = new Vector<Integer>();

        for (int i = 0; i < documentKeyWords.size(); i++) {
            if (include(documentKeyWords.get(i))) {
                vectorOfZeroAndOneForQuerySearch.add(1);
            } else {
                vectorOfZeroAndOneForQuerySearch.add(0);
            }
        }
        return new Vector();
    }

    private List<String> getLemmasList() {
        Iterable<Info> textLemmasIterable = null;
        try {
            textLemmasIterable = lemmaController.parseText(searchRequest);
        } catch (MyStemApplicationException e) {
            e.printStackTrace();
        }

        lemmaController.processQueryInfo(textLemmasIterable);
        return lemmaController.getLemmasList();
    }

}
