package ua.softserve.rv_028.issuecitymonitor.controller.PDF;

import java.util.Date;
import java.util.List;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import ua.softserve.rv_028.issuecitymonitor.entity.enums.PdfTypes;
import static ua.softserve.rv_028.issuecitymonitor.entity.enums.PdfTypes.*;

/**
 * This is to create a PDF file.
 */
public class PDFCreator {
    private final static String[] HEADER_ARRAY = {"#", "Note ID", "Title", "Description", "Category", "User ID", "Initial Date"};
    private final static String[] HEADER_ARRAY_USER = {"#", "USER ID", "First name", "Last name", "Phone", "Email", "User Role", "Reg Date"};
    public final static Font SMALL_BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    public final static Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    public final static Font TITILE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

    public static void addMetaData(Document document) {
        document.addTitle("PDF Report | ICM");
        document.addSubject("Using iText");
        document.addAuthor("SoftServe Academy");
    }

    public static void addContent(Document document, List<DataObject> dataObjList, PdfTypes pdfName) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);

//        System.out.println(pdfName);
//        System.out.println(pdfName.getName());
//        System.out.println(pdfName.getNameUsers());

        if(pdfName.getNameUsers()){
            System.out.println("OKOKOKOKOK");
            createReportTableUser(paragraph, dataObjList);
        }

        else {
            createReportTable(paragraph, dataObjList);
        }


        document.add(paragraph);
    }

    private static void createReportTable(Paragraph paragraph, List<DataObject> dataObjList)
            throws BadElementException {

        float[] columnWidths = {1, 1, 2, 2, 4, 4, 3};

        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        paragraph.add(new Chunk("Report Table: ", SMALL_BOLD));

        if(null == dataObjList){
            paragraph.add(new Chunk("No data to display."));
            return;
        }

        addHeaderInTable(HEADER_ARRAY, table);

        int count = 1;
        for(DataObject dataObject : dataObjList){
            addToTable(table, String.valueOf(count));
            addToTable(table, dataObject.getNoteID());
            addToTable(table, dataObject.getTitle());
            addToTable(table, dataObject.getDesc());
            addToTable(table, dataObject.getCat());
            addToTable(table, dataObject.getUserID());
            addToTable(table, dataObject.getDate());
            count++;
        }
        paragraph.add(table);
    }


    private static void createReportTableUser(Paragraph paragraph, List<DataObject> dataObjList)
            throws BadElementException {

        float[] columnWidths = {1, 2, 2, 2, 4, 4, 3, 3};

        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        paragraph.add(new Chunk("Report Table: ", SMALL_BOLD));

        if(null == dataObjList){
            paragraph.add(new Chunk("No data to display."));
            return;
        }

        addHeaderInTable(HEADER_ARRAY_USER, table);

        int count = 1;
        for(DataObject dataObject : dataObjList){
            addToTable(table, String.valueOf(count));
            addToTable(table, dataObject.getNoteID());
            addToTable(table, dataObject.getTitle());
            addToTable(table, dataObject.getDesc());
            addToTable(table, dataObject.getCat());
            addToTable(table, dataObject.getEmail());
            addToTable(table, dataObject.getUserID());
            addToTable(table, dataObject.getDate());
            count++;
        }
        paragraph.add(table);
    }


    /** Helper methods start here **/
    public static void addTitlePage(Document document, PdfTypes pdfName) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Phrase(pdfName.getName(), PDFCreator.TITILE_FONT));
        addEmptyLine(preface, 1);
        preface.add(new Phrase("Date: ", PDFCreator.SMALL_BOLD));
        preface.add(new Phrase(new Date().toString(), PDFCreator.NORMAL_FONT));
        addEmptyLine(preface, 1);
        preface.add(new Phrase("Report generated by: ", PDFCreator.SMALL_BOLD));
        preface.add(new Phrase("SoftServe", PDFCreator.NORMAL_FONT));
        document.addSubject("PDF : " + pdfName.getName());
        preface.setAlignment(Element.ALIGN_CENTER);
        document.add(preface);
        document.newPage();
    }
    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void addHeaderInTable(String[] headerArray, PdfPTable table){
        PdfPCell c1 = null;
        for(String header : headerArray) {
            c1 = new PdfPCell(new Phrase(header, PDFCreator.SMALL_BOLD));
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
    }


    public static void addToTable(PdfPTable table, String data){
        table.addCell(new Phrase(data, PDFCreator.NORMAL_FONT));
    }


    public static Paragraph getParagraph(){
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(PDFCreator.NORMAL_FONT);
        addEmptyLine(paragraph, 1);
        return paragraph;
    }

}