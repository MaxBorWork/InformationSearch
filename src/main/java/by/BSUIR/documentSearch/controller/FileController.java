package by.BSUIR.documentSearch.controller;

import by.BSUIR.documentSearch.dao.DocumentDao;
import by.BSUIR.documentSearch.model.Document;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.model.Info;

import java.io.*;

public class FileController {

    private DocumentDao documentDao;
    private LemmaController lemmaController;

    public FileController() {
        this.documentDao = new DocumentDao();
        this.lemmaController = new LemmaController();
        parseDirectory("documents");
    }

    private void parseDirectory(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    processFile(file.getName(), folder.getName() + "/" + file.getName());
                }
            }
        }
        lemmaController.setLemmas();
    }

    private void processFile(String name, String path) {
        Iterable<Info> textLemmasIterable = null;
        String fileContent = readFile(path);
        Document document = new Document(name, path, fileContent);
        int documentID = documentDao.getDocumentId(name);
        if (documentID == 0) {
            documentDao.saveDocument(document);
            document.setId(documentDao.getDocumentId(name));
        } else {
            document.setId(documentID);
        }
        try {
            textLemmasIterable = lemmaController.parseText(fileContent);
        } catch (MyStemApplicationException e) {
            e.printStackTrace();
        }
        lemmaController.processDocumentInfo(textLemmasIterable);
        document.setLemmCount(lemmaController.getLemmaCount());
        new LemmaDocumentController().saveLemmaDocument(document.getId(), document.getLemmaCount());
    }

    private String readFile(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n");
            try {
                line = buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
