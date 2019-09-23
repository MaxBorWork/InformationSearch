package by.BSUIR.documentSearch.servlet;

import by.BSUIR.documentSearch.controller.FileController;
import by.BSUIR.documentSearch.controller.MainController;
import by.BSUIR.documentSearch.model.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class SearchServlet extends HttpServlet {
    String reqToSearch = "";
    List<String> resultList = new ArrayList<String>();
    Boolean firstRequest = true;
    List<Document> documentList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        req.setAttribute("resultList", resultList);
        req.setAttribute("reqToSearch", reqToSearch);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String searchRequest = req.getParameter("search");

        if (firstRequest) {
            documentList = new FileController().getDocumentList();
            firstRequest = false;
        }

        System.out.println(searchRequest);

        //TODO(вставка проверкі запроса)

        MainController process = new MainController(searchRequest, documentList);
        resultList = process.getListForResponse();

        reqToSearch = searchRequest;
        doGet(req, resp);
        //  req.getRequestDispatcher("search.jsp").forward(req, resp);
    }
}
