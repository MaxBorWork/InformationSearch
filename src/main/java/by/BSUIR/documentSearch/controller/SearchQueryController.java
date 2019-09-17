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

    public  SearchQueryController (String searchRequest){
        this.searchRequest  = searchRequest;
        this.lemmaController = new LemmaController();
        this.lemmas = getLemmasList();
    }

    public Vector getVectorOfSearchQuery(){
        //TODO  а здесь список из 0 и 1 для запроса нужно сделать
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
