package Persistence;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class PdfWriter {

    //These parameters has to be changed to the value which is read from OPC-UA, right now it is static!

    enum Beers {
        PILSNER,
        WHEAT,
        IPA,
        STOUT,
        ALE,
        ALCOHOL_FREE;
    }
        int batchId = 1337;
        float acceptableAmount = 800;
        float defectAmount = 200;
        float totalAmount = acceptableAmount+defectAmount;
        int deactivated = 12;
        int clearing = 10;
        int stopped = 0;
        int starting = 51;
        int idle = 10;
        int suspended = 21;
        int execute = 35;
        int stopping = 14;
        int aborting = 1;
        int aborted = 12;
        int resetting = 15;
        int completing = 12;
        int complete = 50;
        int deactivating = 0;
        int activating = 10;
        int hum0010 = 45;
        int hum0020 = 30;
        int hum0030 = 40;
        int hum0040 = 50;
        int hum0050 = 60;
        int hum0100 = 70;



    private static String FILE = "c:/temp/FirstPdf.pdf";
        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD);
        private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.NORMAL, BaseColor.RED);
        private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                Font.BOLD);
        private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.BOLD);

        public void printer() {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(FILE));
                document.open();
                addMetaData(document);
                addTitlePage(document);
                addContent(document);
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("PDF CREATED");
        }

        private static void getInstance(Document document, FileOutputStream fileOutputStream) {
        }

        // This is metadata which can be added to the PDF which can be viewed in your Adobe
        // Reader
        // under File -> Properties
        private static void addMetaData(Document document) {
            document.addTitle("Batch report");
            document.addSubject("Using iText");
            document.addKeywords("Java, PDF, ST10");
            document.addAuthor("ST10");
            document.addCreator("ST10");
        }

        private void addTitlePage(Document document)
                throws DocumentException {
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            preface.add(new Paragraph("BATCH #" + (batchId) + " REPORT", catFont));

            addEmptyLine(preface, 1);
            // Will create: Report generated by: _name, _date
            preface.add(new Paragraph(
                    "Report generated by: " + System.getProperty("ST10") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    smallBold));
            addEmptyLine(preface, 3);
            preface.add(new Paragraph(
                    "This document contains the report data of batch#" + batchId,
                    smallBold));

            addEmptyLine(preface, 8);

            preface.add(new Paragraph(
                    "Vi har fucking travlt :-)",
                    redFont));

            document.add(preface);
            // Start a new page
            document.newPage();
        }

        private void addContent(Document document) throws DocumentException {
            Anchor anchor = new Anchor("Batch #" + batchId, catFont);
            anchor.setName("Beer type:" + Beers.PILSNER.describeConstable());

            // Second parameter is the number of the chapter
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);

            Paragraph subPara = new Paragraph("Production amounts:", subFont);
            Section subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Total amount produced: " + totalAmount));
            subCatPart.add(new Paragraph("Acceptable amounts produced: " + acceptableAmount));
            subCatPart.add(new Paragraph("Total amount produced: " + defectAmount));

            subPara = new Paragraph("Amount of time spent, in different states", subFont);
            subCatPart = catPart.addSection(subPara);



            // add a list
            createList(subCatPart);
            Paragraph paragraph = new Paragraph();
            addEmptyLine(paragraph, 5);
            subCatPart.add(paragraph);

            // add a table
            createTable(subCatPart);

            // now add all this to the document
            document.add(catPart);

            // Next section
            anchor = new Anchor("Second Chapter", catFont);
            anchor.setName("Second Chapter");

            // Second parameter is the number of the chapter
            catPart = new Chapter(new Paragraph(anchor), 1);

            subPara = new Paragraph("Subcategory", subFont);
            subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("This is a very important message"));

            // now add all this to the document
            document.add(catPart);

        }

        private void createTable(Section subCatPart)
                throws BadElementException {
            PdfPTable table = new PdfPTable(2);

            // t.setBorderColor(BaseColor.GRAY);
            // t.setPadding(4);
            // t.setSpacing(4);
            // t.setBorderWidth(1);

            PdfPCell c1 = new PdfPCell(new Phrase("State"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Time/sec"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);


            table.setHeaderRows(1);

            table.addCell("Deactivated");
            table.addCell(String.valueOf(deactivated));
            table.addCell("Clearing");
            table.addCell(String.valueOf(clearing));
            table.addCell("Stopped");
            table.addCell(String.valueOf(stopped));
            table.addCell("Starting");
            table.addCell(String.valueOf(starting));
            table.addCell("Idle");
            table.addCell(String.valueOf(idle));
            table.addCell("Suspended");
            table.addCell(String.valueOf(suspended));
            table.addCell("Execute");
            table.addCell(String.valueOf(execute));
            table.addCell("Stopping");
            table.addCell(String.valueOf(stopping));
            table.addCell("Aborting");
            table.addCell(String.valueOf(aborting));
            table.addCell("Aborted");
            table.addCell(String.valueOf(aborted));
            table.addCell("Resetting");
            table.addCell(String.valueOf(resetting));
            table.addCell("Completing");
            table.addCell(String.valueOf(completing));
            table.addCell("Complete");
            table.addCell(String.valueOf(complete));
            table.addCell("Deactivating");
            table.addCell(String.valueOf(deactivating));
            table.addCell("Activating");
            table.addCell(String.valueOf(activating));
            subCatPart.add(table);

            PdfPTable humidities = new PdfPTable(2);

            // t.setBorderColor(BaseColor.GRAY);
            // t.setPadding(4);
            // t.setSpacing(4);
            // t.setBorderWidth(1);

            PdfPCell c2 = new PdfPCell(new Phrase("Timestamp"));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            humidities.addCell(c2);

            c2 = new PdfPCell(new Phrase("humidity"));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            humidities.addCell(c2);

            humidities.addCell(String.valueOf(hum0010));
            humidities.addCell(String.valueOf(deactivated));
            subCatPart.add(humidities);

        }

        private static void createList(Section subCatPart) {
            List list = new List(true, false, 10);
            list.add(new ListItem("Amount of time spent in different states"));
            subCatPart.add(list);
        }

        private static void addEmptyLine(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
            }
        }
    }




