package com.example.kursovaia;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.itextpdf.text.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class History {
    @FXML
    private Label user_name;
    @FXML
    private Button history;
    @FXML
    private Button disease;
    @FXML
    private Button historyv;
    @FXML
    private Label user_post;
    @FXML
    private Button zapis;
    @FXML
    private Button exit;

    Controller ct = null;
    Main aa = null;
    String t = ct.dannie;
    BD BD = null;
    MainClient asd = null;

    MainHisryv mh = null;
    MainDisease zs = null;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ct = new Controller();
        BD = new BD();
        asd = new MainClient();
        aa = new Main();
        mh = new MainHisryv();
        zs = new MainDisease();
        ArrayList <String> adm = BD.Login();
        ArrayList <String> zen = BD.Post();
        int i = 0;
        for (int a = 0; a < BD.Login().size(); a++) {
            if (t.equals(BD.Login().get(a))) {
                i = a;
            }
        }
        if (zen.get(i).equals("Работник")) {
            history.setVisible(false);
            historyv.setVisible(false);
            disease.setVisible(false);
        } else if (zen.get(i).equals("Доктор")) {
            history.setVisible(false);
            zapis.setVisible(false);
            historyv.setVisible(false);
        }
        user_name.setText(BD.FIO().get(i));
        user_post.setText(BD.Post().get(i));
        zapis.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage1 = new Stage();
                try {
                    asd.start(stage1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage2 = (Stage) zapis.getScene().getWindow();
                stage2.close();
            }
        });
        history.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    pdf();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        historyv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage_hist = new Stage();
                try {
                    mh.start(stage_hist);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        disease.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage_hist = new Stage();
                try {
                    zs.start(stage_hist);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage_e = new Stage();
                try {
                    aa.start(stage_e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage_p = (Stage) exit.getScene().getWindow();
                stage_p.close();
            }
        });
    }
    public void pdf() throws FileNotFoundException, DocumentException, SQLException, ClassNotFoundException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("sum.pdf"));

        document.open();


        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table);


        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Service", "Suma")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) throws SQLException, ClassNotFoundException {
        Font font = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        ArrayList<String> name = BD.Specialization();
        ArrayList<String> income = BD.Income_s();

        for(int i = 0; i< name.size();i++) {
            table.addCell(new Paragraph(name.get(i), font));
            table.addCell(new Paragraph(income.get(i), font));
        }

    }
}
