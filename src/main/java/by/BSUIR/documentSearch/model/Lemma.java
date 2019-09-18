package by.BSUIR.documentSearch.model;

public class Lemma {
    private int id;
    private String name;
    private int numOfDocsContains;

    public Lemma(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Lemma() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDocsContains() {
        return numOfDocsContains;
    }

    public void setNumOfDocsContains(int numOfDocsContains) {
        this.numOfDocsContains = numOfDocsContains;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
