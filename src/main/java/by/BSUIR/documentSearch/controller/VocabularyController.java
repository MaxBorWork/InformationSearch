package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.model.Lemma;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VocabularyController {
    private Integer numberOfDocInBase;

    private List<Double> weightList;
    private List<Lemma> lemmList; //список лемм
    private List<Vector<Document>> documentVector; //вектор документа

    public VocabularyController(List<Lemma> lemmList, List<Vector<Document>> documentVector) {

        this.numberOfDocInBase = documentVector.size();
        this.lemmList = lemmList;
        this.documentVector = documentVector;
        this.weightList = new ArrayList<Double>();
    }


    void createVocabulary() {///ATTENTION  VOID
        for(int i = 0; i < lemmList.size(); i++){
//            weightList.add(getNumberOfDocWithThisLemm(lemmList[i]), );
        }
    }

    void getNumberOfDocWithThisLemm(Lemma lemm){
        //TODO(Йо-хо-хо)
    }

//    Double calculateWeight(){
//
//return Math.log()    }
}
