package by.BSUIR.documentSearch.dao;

import by.BSUIR.documentSearch.Constant;
import by.BSUIR.documentSearch.model.Lemma;
import by.BSUIR.documentSearch.model.LemmaDocument;
import org.apache.log4j.Logger;

import java.sql.*;
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

    public void saveLemmaCount(String lemma, int count) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_LEMMA_QUERY);
            preparedStatement.setString(1, lemma);
            preparedStatement.setInt(2, count);
            preparedStatement .executeUpdate();

            con.close();
            log.info("lemmaDocument saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDistinctDocumentsForLemma(int id) {
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
}
