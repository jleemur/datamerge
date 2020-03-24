package datamerge;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import datamerge.parsers.RecordParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** CsvRecordParser */
public class CsvRecordParser implements RecordParser {
  private String filePath;

  public CsvRecordParser(final String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<Record> toRecord() {
    List<Record> records = new ArrayList<>();
    Map<String, String> values;

    try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(filePath)); ) {
      // Iterate over records
      while ((values = reader.readMap()) != null) {
        Record record =
            new Record(
                values.get(CLIENT_ADDRESS_NAME),
                values.get(CLIENT_GUID_NAME),
                values.get(REQUEST_TIME_NAME),
                values.get(SERVICE_GUID_NAME),
                Long.parseLong(values.get(RETRIES_REQUEST_NAME)),
                Long.parseLong(values.get(PACKETS_REQUESTED_NAME)),
                Long.parseLong(values.get(PACKETS_SERVICED_NAME)),
                Long.parseLong(values.get(MAX_HOLE_SIZE_NAME)));

        if (record.isValid()) {
          records.add(record);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return records;
  }
}
