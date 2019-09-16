package by.BSUIR.documentSearch.dao;

import by.BSUIR.documentSearch.Constant;
import by.BSUIR.documentSearch.model.Document;
import by.BSUIR.documentSearch.model.Lemm;
import org.apache.log4j.Logger;

import java.sql.*;

public class LemmaDao {

    private Logger log = Logger.getLogger(DocumentDao.class);

    public LemmaDao() {
        try {
            log.info("creating datatables");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);

            Statement statement = con.createStatement();

            statement.execute(Constant.SQL_CREATE_LEMMA_TABLE);

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLemma(Lemm lemm) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (lemm != null) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_LEMMA_QUERY);
                preparedStatement.setString(1, lemm.getName());
                preparedStatement.setDouble(2, lemm.getWeight());
                preparedStatement .executeUpdate();

                con.close();
                log.info("lemma " + lemm.getName() + " saved");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lemm getLemma(String name) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (!name.equals("")) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_LEMMA_QUERY);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                Lemm lemm = new Lemm();
                if (resultSet.next()) {
                    lemm.setName(resultSet.getString(1));
                    lemm.setWeight(resultSet.getDouble(2));
                    con.close();

                    log.info("found lemma " + lemm.getName());
                    return lemm;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteLemma(String name) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (!name.equals("")) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_DELETE_LEMMA_QUERY);
                preparedStatement.setString(1, name);
                preparedStatement .executeUpdate();

                con.close();
                log.info("lemma " + name + " deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
