package datamerge;

/**
 * App is the main entry point to this project. It is reponsible for creating a DataMerge Object for
 * a list of RecordParser's. When it runs, Record's will be filtered, sorted by request-time, and
 * written out to a final report called `OUTPUT_FILE_PATH`.
 *
 * @author Jonathan Murray
 */
public class App {
  private static final String FILE_PATH = "src/main/resources/";
  private static final String CSV_FILE_PATH = FILE_PATH + "reports.csv";
  private static final String JSON_FILE_PATH = FILE_PATH + "reports.json";
  private static final String XML_FILE_PATH = FILE_PATH + "reports.xml";
  private static final String OUTPUT_FILE_PATH = FILE_PATH + "output.csv";

  public static void main(String[] args) {
    /**
     * Run data merge against a hardcoded list of files. Could make this dynamic and read from stdin
     */
    DataMerge dataMerge =
        new DataMerge(
            new CsvRecordParser(CSV_FILE_PATH),
            new JsonRecordParser(JSON_FILE_PATH),
            new XmlRecordParser(XML_FILE_PATH));
    dataMerge.run(OUTPUT_FILE_PATH);
  }
}
