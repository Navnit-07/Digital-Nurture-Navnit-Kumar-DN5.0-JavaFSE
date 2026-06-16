// Step 5: Test class to demonstrate Factory Method Pattern
public class FactoryMethodTest {

    public static void main(String[] args) {

        System.out.println("=== Factory Method Pattern Demo ===\n");

        // Create Word document using Word factory
        System.out.println("Creating Word Document:");
        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.useDocument();

        System.out.println();

        // Create PDF document using PDF factory
        System.out.println("Creating PDF Document:");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        pdfFactory.useDocument();

        System.out.println();

        // Create Excel document using Excel factory
        System.out.println("Creating Excel Document:");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        excelFactory.useDocument();

        System.out.println();

        // Another way: directly call createDocument()
        System.out.println("Direct creation using createDocument():");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
    }
}
