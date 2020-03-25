# Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

## How to run

`output.csv` is written to `src/main/resources/.`
```
java -cp target/datamerge-1.0-SNAPSHOT-shaded.jar datamerge.App
```

To create a new .jar
```
maven package
```

Run unit tests:
```
maven test
```

## Build requirements

```
JDK 13.0.2
Apache Maven 3.6.3
```

## Resources

```
https://maven.apache.org/guides/getting-started/index.html
https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
```

## Explanation

- Maven as a build tool to make testing, formatting, installing dependencies, and packaging easy!
- https://github.com/coveooss/fmt-maven-plugin : Linting to keep code clean
- https://maven.apache.org/plugins/maven-shade-plugin/ : Package all dependencies into an UBER-jar!
- https://mvnrepository.com/artifact/org.json/json : Simple JSON parsing
- https://mvnrepository.com/artifact/com.opencsv/opencsv : CSV parsing


### How I handled time zones
I learned time zone ids aren't standardized, so Java doesn't have a clean way to work with them; especially ADT.. (https://stackoverflow.com/questions/44811282/java-how-to-get-list-of-time-zone-id-s-for-the-given-time-zone-abbreviation)

I found a list of Canadian time zones and created a custom map to override java.time.ZoneId (https://www.timetemperature.com/abbreviations/canada_time_zone_abbreviations.shtml)

I convert all time zones to GMT before outputting them

### Potential improvements
- Adding javadocs comments and generating them using http://maven.apache.org/plugins/maven-javadoc-plugin/
- Create a class for the custom timestamp used in data files `yyyy-MM-dd HH:mm:ss z`
- Ingest stdin to get a list of files to parse, map them to a RecordParser
- Optimizations
    - Look into performance of RecordParser's
    - In DataMerge.run(), reduce the amount of times I iterate through Record's
