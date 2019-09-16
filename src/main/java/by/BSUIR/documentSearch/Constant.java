package by.BSUIR.documentSearch;

public class Constant {

    public static String dbUrl = "jdbc:mysql://localhost:3306/documentSearch?useUnicode=true&characterEncoding=utf8";;
    public static String dbUser = "root";
    public static String dbPassword = "root";
    public static String SQL_CREATE_DOCUMENT_TABLE = "CREATE TABLE Document (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "title varchar(255),\n" +
            "path varchar(255),\n" +
            "text mediumtext\n" +
            ")";

    public static String SQL_CREATE_LEMMA_TABLE = "CREATE TABLE Lemma (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "name varchar(255),\n" +
            "weight double,\n" +
            ")";

    public static String SQL_CREATE_LEMMA_DOCUMENT_TABLE = "CREATE TABLE LemmaDocument (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "lemma_id int NOT NULL,\n" +
            "document_id int NOT NULL,\n" +
            "num_of_repeats int,\n" +
            "lemma_weight double,\n" +
            ")";

    public static String SQL_GET_COL_OF_DOCUMENTS_QUERY = "SELECT COUNT(id) FROM Document";

    public static String SQL_GET_DISTINCT_DOCUMENTS_FOR_LEMMA_QUERY = "SELECT DISTINCT document_id FROM LemmaDocument WHERE lemma_id=?";

    public static String SQL_GET_LEMMAS_FROM_DOCUMENT_QUERY = "SELECT * FROM LemmaDocument WHERE document_id=?";

    public static String SQL_INSERT_DOCUMENT_QUERY = "INSERT INTO Document" +
            "(title, text)" +
            "VALUES (?,?)";

    public static String SQL_INSERT_LEMMA_QUERY = "INSERT INTO Lemma" +
            "(name, weight)" +
            "VALUES (?,?)";

    public static String SQL_GET_LEMMAS_QUERY = "SELECT (name, weight) FROM Lemma";

    public static String SQL_GET_LEMMA_QUERY = "SELECT (name, weight) FROM Lemma WHERE name=?";

    public static String SQL_DELETE_DOCUMENT_QUERY = "DELETE FROM Document WHERE title=?";

    public static String SQL_DELETE_LEMMA_QUERY = "DELETE FROM Lemma WHERE name=?";
}
