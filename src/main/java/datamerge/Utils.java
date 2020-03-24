package datamerge;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Utils {
  private Utils() {}

  public static String epochToDate(long ms) {
    // input e.g. 1467175919322L
    DateTimeFormatter dtf =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("GMT"));
    return dtf.format(Instant.ofEpochMilli(ms));
  }

  public static String timezoneToGMT(String date) {
    // input e.g. "2016-06-29 07:22:30 ADT"
    // Since ADT is not a part of ZoneId, creating a map for Canadian timezones
    // https://stackoverflow.com/questions/44811282/java-how-to-get-list-of-time-zone-id-s-for-the-given-time-zone-abbreviation
    // https://www.timetemperature.com/abbreviations/canada_time_zone_abbreviations.shtml
    Map<String, String> canadaTimezoneMap =
        Map.ofEntries(
            Map.entry("NST", "-0330"),
            Map.entry("NDT", "-0230"),
            Map.entry("AST", "-0400"),
            Map.entry("ADT", "-0300"),
            Map.entry("EST", "-0500"),
            Map.entry("EDT", "-0400"),
            Map.entry("CST", "-0600"),
            Map.entry("CDT", "-0500"),
            Map.entry("MST", "-0700"),
            Map.entry("MDT", "-0600"),
            Map.entry("PST", "-0800"),
            Map.entry("PDT", "-0700"),
            Map.entry("YST", "-0800"),
            Map.entry("YDT", "-0700"));

    // Parse date
    String[] dateArr = date.split(" ");
    String[] ymd = dateArr[0].split("-");
    String[] hms = dateArr[1].split(":");
    int year = Integer.parseInt(ymd[0]);
    int month = Integer.parseInt(ymd[1]);
    int day = Integer.parseInt(ymd[2]);
    int hour = Integer.parseInt(hms[0]);
    int minute = Integer.parseInt(hms[1]);
    int second = Integer.parseInt(hms[2]);

    // Setup LocalDateTime object
    LocalDateTime localTime = LocalDateTime.of(year, month, day, hour, minute, second);

    // Convert to ZonedDateTime
    String timezone = dateArr[2];
    ZonedDateTime zonedTime = ZonedDateTime.of(localTime, ZoneId.of(timezone, canadaTimezoneMap));

    DateTimeFormatter dtf =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("GMT"));
    return dtf.format(zonedTime);
  }
}
