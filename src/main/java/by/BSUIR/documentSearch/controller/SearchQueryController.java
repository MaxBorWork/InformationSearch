package by.BSUIR.documentSearch.controller;

import java.util.List;
import java.util.Vector;

public class SearchQueryController {
    private String searchRequest;
    private List lemListAll;

    public  SearchQueryController (String searchRequest){
        this.searchRequest  = searchRequest;
        //TODO здесь нужен список лемм
    }

    public Vector getVectorOfSearchQuary(){
        //TODO  а здесь список из 0 и 1 для запроса нужно сделать
        return new Vector();
    }
}
