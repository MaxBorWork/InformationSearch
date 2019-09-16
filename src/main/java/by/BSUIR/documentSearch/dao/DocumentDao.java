package by.BSUIR.documentSearch.dao;

import by.BSUIR.documentSearch.Constant;
import by.BSUIR.documentSearch.model.Document;
import org.apache.log4j.Logger;

import java.sql.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DocumentDao {

    private Logger log = Logger.getLogger(DocumentDao.class);

    private String dbUrl = "jdbc:mysql://localhost:3306/documentSearch?useUnicode=true&characterEncoding=utf8";;
    private String dbUser = "root";
    private String dbPassword = "root";
    private String SQL_CREATE_DOCUMENT_TABLE = "CREATE TABLE Document";


    public DocumentDao() {
        try {
            log.info("creating datatables");
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);

            Statement statement = con.createStatement();

            statement.execute(Constant.SQL_CREATE_DOCUMENT_TABLE);

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveDocument(Document document) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (document != null) {
                PreparedStatement ps = con.prepareStatement(Constant.SQL_INSERT_DOCUMENT_QUERY);
                ps.setString(1, document.getTitle());
                ps.setString(2, document.getText());
                ps .executeUpdate();
                con.close();
                log.info("document " + document.getTitle() + " saved");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDocument(String title) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (!title.equals("")) {

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_DELETE_DOCUMENT_QUERY);
                preparedStatement.setString(1, title);
                preparedStatement .executeUpdate();

                con.close();
                log.info("document " + title + " deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
