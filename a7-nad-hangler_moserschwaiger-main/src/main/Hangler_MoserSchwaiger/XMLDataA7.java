package Hangler_MoserSchwaiger;

import assignment7_int.XMLData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Optional;

/**
 * Class to read an XML-File and
 * build a data structure to manage a list of different items (Book, Cd or List)
 * based on the Composite Pattern
 */
public class XMLDataA7 implements XMLData {

    private ItemList itemList;

    @Override
    public void loadXml(File input) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        Element root = doc.getDocumentElement();
        itemList = new ItemList(root.getAttribute("name"));

        this.loadXmlItems(root, itemList);

        //itemList.displayItemList();
    }

    /**
     * loads the elements of the XML-File as items(Book, cd or list) into a given itemList
     * @param root the root element of the file
     * @param itemList the itemList to load into
     * @return the updated ItemList
     */
    private IItem loadXmlItems(Element root, ItemList itemList) throws Exception {
        NodeList nl = root.getChildNodes();

        for (int i=0;i<nl.getLength();i++){
            Node n = nl.item(i);
            int type = n.getNodeType();

            if (type == Node.ELEMENT_NODE) {
                Element e = (Element) n;

                //add item to the current list
                switch (e.getTagName()) {
                    case "book" -> itemList.addItem(new Book(e.getAttribute("name"), Double.parseDouble(e.getAttribute("price")), Long.parseLong(e.getAttribute("isbn"))));
                    case "cd" -> itemList.addItem(new Cd(e.getAttribute("name"), Double.parseDouble(e.getAttribute("price"))));
                    case "list" -> itemList.addItem(this.loadXmlItems(e,new ItemList(e.getAttribute("name"))));
                    default -> throw new Exception("Unknown Type : " + e.getTagName());
                }
            }
        }
        return itemList;
    }

    @Override
    public Optional<Double> getPrice(String item) {
        //if main list (root-list) is the item to search for
        if(this.itemList.getName().equals(item)) {
            return Optional.of(this.itemList.getPrice());
        }

        return this.itemList.getItem(item) != null ? Optional.of(this.itemList.getItem(item).getPrice()) :  Optional.empty();
    }
}
