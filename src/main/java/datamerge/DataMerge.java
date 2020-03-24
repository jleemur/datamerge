package datamerge;

import datamerge.parsers.RecordParser;
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
    // writeReportCSV(fileOut);
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

  // private void writeReportCSV() {

  // }
}
