# Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

### Build Requirements

```
JDK 13.0.2
Apache Maven 3.6.3
```

### Resources

```
https://maven.apache.org/guides/getting-started/index.html
https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
```