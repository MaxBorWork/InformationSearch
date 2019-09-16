package by.BSUIR.documentSearch.model;

public class Lemm {
    private String name;
    private double weight;
    private int numOfDocsContains;

    public Lemm(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public Lemm() {
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
