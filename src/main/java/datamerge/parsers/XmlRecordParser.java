package datamerge;

import datamerge.parsers.RecordParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** XmlRecordParser */
public class XmlRecordParser implements RecordParser {
  private String filePath;

  public XmlRecordParser(final String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<Record> toRecord() {
    List<Record> records = new ArrayList<>();

    try {
      File file = new File(filePath);
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(file);
      doc.getDocumentElement().normalize(); // normalize document

      NodeList nodeList = doc.getElementsByTagName("report");

      // Iterate over records
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;
          Record record =
              new Record(
                  getNodeValue(element, CLIENT_ADDRESS_NAME),
                  getNodeValue(element, CLIENT_GUID_NAME),
                  getNodeValue(element, REQUEST_TIME_NAME),
                  getNodeValue(element, SERVICE_GUID_NAME),
                  Long.parseLong(getNodeValue(element, RETRIES_REQUEST_NAME)),
                  Long.parseLong(getNodeValue(element, PACKETS_REQUESTED_NAME)),
                  Long.parseLong(getNodeValue(element, PACKETS_SERVICED_NAME)),
                  Long.parseLong(getNodeValue(element, MAX_HOLE_SIZE_NAME)));

          if (record.isValid()) {
            records.add(record);
          }
        } else {
          // skip
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }
    return records;
  }

  private String getNodeValue(Element element, String name) {
    return element.getElementsByTagName(name).item(0).getTextContent();
  }
}
