package by.BSUIR.documentSearch.dao;

import by.BSUIR.documentSearch.Constant;
import by.BSUIR.documentSearch.model.Lemma;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void saveLemma(Lemma lemma) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (lemma != null) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_LEMMA_QUERY);
                preparedStatement.setString(1, lemma.getName());
                preparedStatement .executeUpdate();

                con.close();
                log.info("lemma " + lemma.getName() + " saved");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lemma getLemma(String name) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (!name.equals("")) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_GET_LEMMA_QUERY);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                Lemma lemma = new Lemma();
                if (resultSet.next()) {
                    lemma.setId(resultSet.getInt(1));
                    lemma.setName(resultSet.getString(2));
                    con.close();

                    log.info("found lemma " + lemma.getName());
                    return lemma;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lemma> getLemmas() {
        List<Lemma> lemmata = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(Constant.SQL_GET_LEMMAS_QUERY);
            while (resultSet.next()) {
                lemmata.add(new Lemma(resultSet.getInt(1), resultSet.getString(2)));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lemmata;
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
