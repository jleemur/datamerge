package datamerge.parsers;

import datamerge.Record;
import java.util.List;

public interface RecordParser {
  public final String CLIENT_ADDRESS_NAME = "client-address";
  public final String CLIENT_GUID_NAME = "client-guid";
  public final String REQUEST_TIME_NAME = "request-time";
  public final String SERVICE_GUID_NAME = "service-guid";
  public final String RETRIES_REQUEST_NAME = "retries-request";
  public final String PACKETS_REQUESTED_NAME = "packets-requested";
  public final String PACKETS_SERVICED_NAME = "packets-serviced";
  public final String MAX_HOLE_SIZE_NAME = "max-hole-size";

  public List<Record> toRecord();
}
