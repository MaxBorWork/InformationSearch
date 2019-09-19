package by.BSUIR.documentSearch;

public class Constant {

    public static String dbUrl = "jdbc:mysql://maxbor.cpl.by:1340/informationSearch?useUnicode=true&characterEncoding=utf8";;
    public static String dbUser = "root";
    public static String dbPassword = "root";
    public static String SQL_CREATE_DOCUMENT_TABLE = "CREATE TABLE IF NOT EXISTS Document (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "title varchar(255),\n" +
            "path varchar(255),\n" +
            "text mediumtext,\n" +
            "col_of_words int,\n" +
            "PRIMARY KEY (id)"+
            ")";

    public static String SQL_CREATE_LEMMA_TABLE = "CREATE TABLE IF NOT EXISTS Lemma (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "name varchar(255),\n" +
            "isKey int,\n" +
            "PRIMARY KEY (id)"+
            ")";

    public static String SQL_CREATE_LEMMA_DOCUMENT_TABLE = "CREATE TABLE IF NOT EXISTS LemmaDocument (" +
            "id int NOT NULL AUTO_INCREMENT,\n" +
            "lemma_id int NOT NULL,\n" +
            "document_id int NOT NULL,\n" +
            "num_of_repeats int,\n" +
            "lemma_weight double,\n" +
            "key_lemma_weight double,\n" +
            "PRIMARY KEY (id)"+
            ")";

    public static String SQL_GET_COL_OF_DOCUMENTS_QUERY = "SELECT COUNT(id) FROM Document";

    public static String SQL_GET_DISTINCT_DOCUMENTS_FOR_LEMMA_QUERY = "SELECT DISTINCT document_id FROM LemmaDocument WHERE lemma_id=?";

    public static String SQL_GET_LEMMAS_FROM_DOCUMENT_QUERY = "SELECT * FROM LemmaDocument WHERE document_id=?";

    public static String SQL_GET_LEMMA_COUNT_FROM_DOCUMENT_QUERY = "SELECT num_of_repeats" +
                                                                    "FROM LemmaDocument" +
                                                                    "WHERE document_id=? AND lemma_id=?";

    public static String SQL_INSERT_DOCUMENT_QUERY = "INSERT INTO Document" +
            "(title, path, text)" +
            "VALUES (?,?,?)";

    public static String SQL_INSERT_LEMMA_QUERY = "INSERT INTO Lemma" +
            "(name)" +
            "VALUES (?)";

    public static String SQL_INSERT_LEMMA_DOCUMENT_QUERY = "INSERT INTO LemmaDocument" +
            "(lemma_id, document_id, num_of_repeats)" +
            "VALUES (?,?,?)";

    public static String SQL_SET_LEMMA_WEIGHT_FOR_DOCUMENT_QUERY = "UPDATE LemmaDocument" +
            "SET lemma_weight=?" +
            "WHERE document_id=? AND lemma_id=?";

//    public static final String SQL_UPDATE_LEMMA_DOCUMENT_QUERY = "UPDATE LemmaDocument SET num_of_repeats=?" +
//            " WHERE LemmaDocument.lemma_id ";

    public static String SQL_GET_LEMMAS_QUERY = "SELECT id, name FROM Lemma";

    public static String SQL_GET_LEMMA_NAMES_QUERY = "SELECT name FROM Lemma";

    public static String SQL_GET_DOCUMENTS_QUERY = "SELECT * FROM Document";

    public static String SQL_GET_DOCUMENT_ID_QUERY = "SELECT id FROM Document WHERE title=?";

    public static String SQL_GET_DOCUMENT_COL_OF_WORDS_QUERY = "SELECT col_of_words FROM Document WHERE id=?";

    public static String SQL_GET_LEMMA_QUERY = "SELECT id, name FROM Lemma WHERE name=?";

    public static String SQL_DELETE_DOCUMENT_QUERY = "DELETE FROM Document WHERE title=?";

    public static String SQL_DELETE_LEMMA_QUERY = "DELETE FROM Lemma WHERE name=?";
}
