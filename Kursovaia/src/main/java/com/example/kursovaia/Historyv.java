package com.example.kursovaia;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Historyv {
    @FXML
    Label lab;

    BD BD = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, DocumentException, FileNotFoundException {
        BD = new BD();

        pdf();
        last_enter();

    }

    public void pdf () throws FileNotFoundException, DocumentException, SQLException, ClassNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Last_enter.pdf"));

        document.open();


        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table);


        document.add(table);
        document.close();


    }


    private void addRows (PdfPTable table) throws SQLException, ClassNotFoundException {
        Font font = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        ArrayList<String> post_db = BD.FIO();
        ArrayList<String> fio_db = BD.Post();
        ArrayList<String> enter_db = BD.Last_enter();

        for (int i = 0; i < fio_db.size(); i++) {
            table.addCell(new Paragraph(post_db.get(i), font));
            table.addCell(new Paragraph(fio_db.get(i), font));
            table.addCell(enter_db.get(i));
        }
    }

    private void addTableHeader (PdfPTable table){
        Stream.of("FIO", "Post", "Last Enter")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    public void last_enter() {
        Process p;

        {
            try {
                p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\fancy\\Downloads\\Last_enter.pdf");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
