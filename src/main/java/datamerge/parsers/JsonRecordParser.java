package datamerge;

import datamerge.parsers.RecordParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** JsonRecordParser */
public class JsonRecordParser implements RecordParser {
  private String filePath;

  public JsonRecordParser(final String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<Record> toRecord() {
    List<Record> records = new ArrayList<>();
    JSONParser parser = new JSONParser();

    try (FileReader reader = new FileReader(filePath); ) {
      Object file = parser.parse(reader);
      JSONArray jsonObjects = (JSONArray) file;

      // Iterate over records
      for (Object o : jsonObjects) {
        JSONObject obj = (JSONObject) o;
        Record record =
            new Record(
                (String) obj.get(CLIENT_ADDRESS_NAME),
                (String) obj.get(CLIENT_GUID_NAME),
                (Long) obj.get(REQUEST_TIME_NAME),
                (String) obj.get(SERVICE_GUID_NAME),
                (Long) obj.get(RETRIES_REQUEST_NAME),
                (Long) obj.get(PACKETS_REQUESTED_NAME),
                (Long) obj.get(PACKETS_SERVICED_NAME),
                (Long) obj.get(MAX_HOLE_SIZE_NAME));

        if (record.isValid()) {
          records.add(record);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return records;
  }
}
