package datamerge;

/** Record */
public class Record {
  private String clientAddress; // e.g. "124.253.117.135"
  private String clientGUID; // e.g. "7b5b34c5-2d3a-46c8-b462-58ee432c8f0f"
  private String requestTime; // e.g. "2016-06-29 07:22:30 GMT"
  private String serviceGUID; // e.g. "d15e4790-67f7-4488-a28d-32ced8673c58"
  private long retriesRequest; // e.g. 5
  private long packetsRequested; // e.g. 15
  private long packetsServiced; // e.g. 13
  private long maxHoleSize; // e.g. 11

  public Record(
      String clientAddress,
      String clientGUID,
      String requestTime,
      String serviceGUID,
      long retriesRequest,
      long packetsRequested,
      long packetsServiced,
      long maxHoleSize) {
    this.clientAddress = clientAddress;
    this.clientGUID = clientGUID;
    this.requestTime = Utils.timezoneToGMT(requestTime);
    this.serviceGUID = serviceGUID;
    this.retriesRequest = retriesRequest;
    this.packetsRequested = packetsRequested;
    this.packetsServiced = packetsServiced;
    this.maxHoleSize = maxHoleSize;
  }

  public Record(
      String clientAddress,
      String clientGUID,
      long requestTime,
      String serviceGUID,
      long retriesRequest,
      long packetsRequested,
      long packetsServiced,
      long maxHoleSize) {
    this.clientAddress = clientAddress;
    this.clientGUID = clientGUID;
    this.requestTime = Utils.epochToDate(requestTime);
    this.serviceGUID = serviceGUID;
    this.retriesRequest = retriesRequest;
    this.packetsRequested = packetsRequested;
    this.packetsServiced = packetsServiced;
    this.maxHoleSize = maxHoleSize;
  }

  public String getRequestTime() {
    return requestTime;
  }

  public String getServiceGUID() {
    return serviceGUID;
  }

  public Boolean isValid() {
    if (packetsServiced != 0) return true;
    return false;
  }

  public String[] toCSV() {
    String[] csv = new String[8];
    csv[0] = clientAddress;
    csv[1] = clientGUID;
    csv[2] = requestTime;
    csv[3] = serviceGUID;
    csv[4] = Long.toString(retriesRequest);
    csv[5] = Long.toString(packetsRequested);
    csv[6] = Long.toString(packetsServiced);
    csv[7] = Long.toString(maxHoleSize);
    return csv;
  }
}
