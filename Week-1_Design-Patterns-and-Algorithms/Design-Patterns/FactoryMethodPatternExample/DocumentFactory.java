// Step 4: Abstract factory class
public abstract class DocumentFactory {

    // Factory Method - each child class will create its own document type
    public abstract Document createDocument();

    // Common method that uses the factory method
    public void useDocument() {
        Document doc = createDocument();
        doc.open();
        doc.save();
        doc.close();
    }
}
