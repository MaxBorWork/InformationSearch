package by.BSUIR.documentSearch.model;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Document {
    private int id;
    private String title;
    private String text;
    private String path;

    private Map<String, Integer> lemmaCount;
    private List<String> keyWords;
    private  Vector<Double> documentVector;


    public Document(String title, String text, int id, String path, Vector<Double> documentVector, Map<String, Integer> lemmaCount, List<String> keyWords) {
        this.title = title;
        this.text = text;
        this.id = id;
        this.path = path;
        this.documentVector = documentVector;
        this.lemmaCount = lemmaCount;
        this.keyWords = keyWords;
    }


    public List<String> getKeyWords() {
        return keyWords;
    }

    public Document(String title, String text, int id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    public Document(String title, String text, Map<String, Integer> lemmaCount) {
        this.title = title;
        this.text = text;
        this.lemmaCount = lemmaCount;
    }

    public Document(int id, String title, String path, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.path = path;
    }

    public Document(String title, String path, String text) {
        this.title = title;
        this.text = text;
        this.path = path;
    }

    public Document(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Document() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector< Double> getDocumentVector() {
        return documentVector;
    }

    public void setDocumentVector(Vector< Double> documentVector) {
        this.documentVector = documentVector;
    }

    public Map<String, Integer> getLemmCount() {
        return lemmaCount;
    }

    public void setLemmCount(Map<String, Integer> lemmCount) {
        this.lemmaCount = lemmCount;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Integer> getLemmaCount() {
        return lemmaCount;
    }

    public void setLemmaCount(Map<String, Integer> lemmaCount) {
        this.lemmaCount = lemmaCount;
    }
}
