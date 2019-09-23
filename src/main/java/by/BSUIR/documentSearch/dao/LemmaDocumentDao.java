package by.BSUIR.documentSearch.dao;

import by.BSUIR.documentSearch.Constant;
import by.BSUIR.documentSearch.model.Lemma;
import by.BSUIR.documentSearch.model.LemmaDocument;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LemmaDocumentDao {

    private Logger log = Logger.getLogger(LemmaDocumentDao.class);

    public LemmaDocumentDao() {
        try {
            log.info("creating datatables");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);

            Statement statement = con.createStatement();

            statement.execute(Constant.SQL_CREATE_LEMMA_DOCUMENT_TABLE);

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLemmaDocument(LemmaDocument lemmaDocument) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_LEMMA_DOCUMENT_QUERY);
            preparedStatement.setInt(1, lemmaDocument.getLemmaID());
            preparedStatement.setInt(2, lemmaDocument.getDocumentID());
            preparedStatement.setInt(3, lemmaDocument.getNumOfRepeats());
            preparedStatement .executeUpdate();

            con.close();
            log.info("lemmaDocument saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveLemmaDocuments(List<LemmaDocument> lemmaDocuments) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_LEMMA_DOCUMENT_QUERY);
            for (LemmaDocument lemmaDocument : lemmaDocuments) {
                preparedStatement.setInt(1, lemmaDocument.getLemmaID());
                preparedStatement.setInt(2, lemmaDocument.getDocumentID());
                preparedStatement.setInt(3, lemmaDocument.getNumOfRepeats());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            con.close();
            log.info("lemmaDocument saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLemmaWeight(int documentID, int lemmaID, double weight) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_SET_LEMMA_WEIGHT_FOR_DOCUMENT_QUERY);
            preparedStatement.setDouble(1, weight);
            preparedStatement.setInt(2, documentID);
            preparedStatement.setInt(3, lemmaID);
            preparedStatement .executeUpdate();

            con.close();
            log.info("lemmaDocument updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLemmaWeights(List<LemmaDocument> lemmaDocuments) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_SET_LEMMA_WEIGHT_FOR_DOCUMENT_QUERY);
            for (LemmaDocument lemmaDocument : lemmaDocuments) {
                preparedStatement.setDouble(1, lemmaDocument.getLemmaWeight());
                preparedStatement.setInt(2, lemmaDocument.getDocumentID());
                preparedStatement.setInt(3, lemmaDocument.getLemmaID());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            con.close();
            log.info("lemmaDocument updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getColOfDocumentsForLemma(int id) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (id != 0) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_DISTINCT_DOCUMENTS_FOR_LEMMA_QUERY);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                int documentsCount = 0;
                if (resultSet.next()) {
                    documentsCount = resultSet.getInt(1);
                    con.close();

                    return documentsCount;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLemmaCountFromDocument(int lemmaID, int documentID) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (documentID != 0 && lemmaID != 0) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_LEMMA_COUNT_FROM_DOCUMENT_QUERY);
                preparedStatement.setInt(1, documentID);
                preparedStatement.setInt(2, lemmaID);
                ResultSet resultSet = preparedStatement.executeQuery();
                int documentsCount = 0;
                if (resultSet.next()) {
                    documentsCount = resultSet.getInt(1);
                    con.close();

                    return documentsCount;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<LemmaDocument> getLemmaCountFromDocument(int documentID) {
        List<LemmaDocument> lemmaDocuments = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (documentID != 0) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_LEMMAS_FROM_DOCUMENT_QUERY);
                preparedStatement.setInt(1, documentID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    lemmaDocuments.add(new LemmaDocument(resultSet.getInt(1),
                                                            resultSet.getInt(2),
                                                            resultSet.getInt(3),
                                                            resultSet.getInt(4),
                                                            resultSet.getDouble(5),
                                                            resultSet.getDouble(6)
                                                        ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lemmaDocuments;
    }

    public List<String> getKeyLemmasFromDocument(int documentID) {
        List<String> keyLemmas = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (documentID != 0) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_KEY_LEMMAS_FROM_DOCUMENT_QUERY);
                preparedStatement.setInt(1, documentID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    keyLemmas.add(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keyLemmas;
    }
}
