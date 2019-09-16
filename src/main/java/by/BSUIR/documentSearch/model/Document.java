package by.BSUIR.documentSearch.model;

import java.util.Map;

public class Document {
    private int id;
    private String title;
    private String text;
    private String path;

    private Map<Integer, Double> documentVector;
    private Map<String, Integer> lemmaCount;

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

    public Document(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Document() {
    }

    //TODO типо у меня есть все эти параметры

    /***
     * numberOfDocInBase - чсило документов в базе
     * numberOfDocWithThisLem - число документов содержащих эти леммы
     * numberOfOccurrencesLemInDoc - число вхождения слова в документ
     * lemVector - вектор лем
     *
     *
     *
     */

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

    public Map<Integer, Double> getDocumentVector() {
        return documentVector;
    }

    public void setDocumentVector(Map<Integer, Double> documentVector) {
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
