package datamerge;

import com.opencsv.CSVWriter;
import datamerge.parsers.RecordParser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/** DataMerge */
public class DataMerge {
  private List<Record> records = new ArrayList<>();

  public DataMerge(RecordParser... parsers) {
    // Create Records, add them to list
    for (RecordParser p : parsers) {
      this.records.addAll(p.toRecord());
    }
  }

  public void run(String fileOut) {
    // Sort all records by request time
    records.sort(
        new Comparator<Record>() {
          public int compare(Record r1, Record r2) {
            return r1.getRequestTime().compareTo(r2.getRequestTime());
          }
        });

    printRecordsPerServiceGUID();
    writeCSVReport(fileOut);
  }

  private void printRecordsPerServiceGUID() {
    HashMap<String, Integer> recordsPerServiceGUID = new HashMap<String, Integer>();
    for (Record r : records) {
      String serviceGUID = r.getServiceGUID();
      if (recordsPerServiceGUID.containsKey(serviceGUID)) {
        recordsPerServiceGUID.put(serviceGUID, recordsPerServiceGUID.get(serviceGUID) + 1);
      } else {
        recordsPerServiceGUID.put(serviceGUID, 1);
      }
    }

    System.out.println("--- # of records per service-guid ---");
    recordsPerServiceGUID
        .entrySet()
        .forEach(
            entry -> {
              System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            });
  }

  private void writeCSVReport(String filePath) {
    File file = new File(filePath);
    try (FileWriter outputfile = new FileWriter(file);
        CSVWriter writer =
            new CSVWriter(
                outputfile,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END); ) {

      // add headers to csv
      String[] header = new String[8];
      header[0] = RecordParser.CLIENT_ADDRESS_NAME;
      header[1] = RecordParser.CLIENT_GUID_NAME;
      header[2] = RecordParser.REQUEST_TIME_NAME;
      header[3] = RecordParser.SERVICE_GUID_NAME;
      header[4] = RecordParser.RETRIES_REQUEST_NAME;
      header[5] = RecordParser.PACKETS_REQUESTED_NAME;
      header[6] = RecordParser.PACKETS_SERVICED_NAME;
      header[7] = RecordParser.MAX_HOLE_SIZE_NAME;
      writer.writeNext(header);

      // add data to csv
      for (Record r : records) {
        writer.writeNext(r.toCSV());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
