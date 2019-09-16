package by.BSUIR.documentSearch.model;

import java.util.Map;
import java.util.Vector;

public class Document {
    private String title;
    private String text;
    private String date;
    private String time;
    private int documentID;

    private Map<Integer, Double> documentVector;
    private Map<String, Integer> lemmCount;

    public Document(String title, String text, String date, String time, int documentID, Map<Integer, Double> documentVector) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.time = time;
        this.documentVector = documentVector;
    }

    public Document(String title, String text, Map<String, Integer> lemmCount) {
        this.title = title;
        this.text = text;
        this.lemmCount = lemmCount;
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
    Integer numberOfDocInBase = 1000;
    Integer numberOfDocWithThisLem = 500;

    public Vector<Double> createDocumentVector() {
        Vector<Double> vectorForDoc = new Vector<>();
        Vector<Double> numerator = new Vector<>();
        Double denominator = 0.0;
        Double amount = 0.0;

        for (int i = 0; i < lemmCount.size(); i++) {
//            numerator.add(this.numberOfOccurrencesLemInDoc(lemmCount.get(i)) * Math.log(numberOfDocInBase / numberOfDocWithThisLem));
        }

        for (int i = 0; i < numerator.size(); i++) {
            amount += numerator.get(i) * numerator.get(i);
        }

        denominator = Math.sqrt(amount);

        for (int i = 0; i < numerator.size(); i++) {
            vectorForDoc.add(numerator.get(i) / denominator);
        }

        return vectorForDoc;
    }

    private int numberOfOccurrencesLemInDoc(String lem) {
        //TODO получить число вхождений лемы в документ
        return 0;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public Map<Integer, Double> getDocumentVector() {
        return documentVector;
    }

    public void setDocumentVector(Map<Integer, Double> documentVector) {
        this.documentVector = documentVector;
    }

    public Map<String, Integer> getLemmCount() {
        return lemmCount;
    }

    public void setLemmCount(Map<String, Integer> lemmCount) {
        this.lemmCount = lemmCount;
    }
}
