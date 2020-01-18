package com.challenge.Iutils;

        import org.apache.commons.csv.CSVRecord;
        import java.nio.file.Path;
        import java.util.List;

public interface ICSVReadFile {

    List<?> CSVRecordsToEntitiesList(Path path, Class<?> classType);

    List<?> itetareCSVRecords(Class<?> classType, List<CSVRecord> records);

    String locatePathFile(String csvFile);

}
