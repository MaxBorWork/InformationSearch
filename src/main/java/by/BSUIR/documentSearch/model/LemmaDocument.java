package by.BSUIR.documentSearch.model;

public class LemmaDocument {
    private int ID;
    private int lemmaID;
    private int documentID;
    private int numOfRepeats;
    private double lemmaWeight;
    private double keyLemmaWeight;

    public LemmaDocument(int ID, int lemmaID, int documentID, int numOfRepeats, double lemmaWeight, double keyLemmaWeight) {
        this.ID = ID;
        this.lemmaID = lemmaID;
        this.documentID = documentID;
        this.numOfRepeats = numOfRepeats;
        this.lemmaWeight = lemmaWeight;
        this.keyLemmaWeight = keyLemmaWeight;
    }

    public LemmaDocument(int lemmaID, int documentID) {
        this.lemmaID = lemmaID;
        this.documentID = documentID;
    }

    public LemmaDocument(int lemmaID, int documentID, int numOfRepeats) {
        this.lemmaID = lemmaID;
        this.documentID = documentID;
        this.numOfRepeats = numOfRepeats;
    }

    public LemmaDocument() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLemmaID() {
        return lemmaID;
    }

    public void setLemmaID(int lemmaID) {
        this.lemmaID = lemmaID;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public int getNumOfRepeats() {
        return numOfRepeats;
    }

    public void setNumOfRepeats(int numOfRepeats) {
        this.numOfRepeats = numOfRepeats;
    }

    public double getLemmaWeight() {
        return lemmaWeight;
    }

    public void setLemmaWeight(double lemmaWeight) {
        this.lemmaWeight = lemmaWeight;
    }

    public double getKeyLemmaWeight() {
        return keyLemmaWeight;
    }

    public void setKeyLemmaWeight(double keyLemmaWeight) {
        this.keyLemmaWeight = keyLemmaWeight;
    }
}
