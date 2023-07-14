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
import java.util.stream.Stream;

public class Disease {
    @FXML
    Label label_b;

    BD BD = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, DocumentException, FileNotFoundException {
        BD = new BD();

        pdf();
        Disease();

    }

    public void pdf () throws FileNotFoundException, DocumentException, SQLException, ClassNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Disease.pdf"));

        document.open();


        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table);


        document.add(table);
        document.close();


    }


    private void addRows (PdfPTable table) throws SQLException, ClassNotFoundException {
        Font font = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        ArrayList<String> fio_db = BD.FIO_Clients();
        ArrayList<String> enter_db = BD.Disease();

        for(int i = 0; i< fio_db.size();i++) {
            table.addCell(new Paragraph(fio_db.get(i), font));
            table.addCell(new Paragraph(enter_db.get(i), font));
        }
    }

    private void addTableHeader (PdfPTable table){
        Stream.of("FIO", "Disease")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    public void Disease() {
        Process p;

        {
            try {
                p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\fancy\\Downloads\\Disease.pdf");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
