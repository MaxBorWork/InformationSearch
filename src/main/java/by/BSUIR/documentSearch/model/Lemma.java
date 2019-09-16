package by.BSUIR.documentSearch.model;

public class Lemma {
    private String name;
    private double weight;
    private int numOfDocsContains;

    public Lemma(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Lemma() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNumOfDocsContains() {
        return numOfDocsContains;
    }

    public void setNumOfDocsContains(int numOfDocsContains) {
        this.numOfDocsContains = numOfDocsContains;
    }
}
