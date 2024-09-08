package utils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneralReusableLibrary {


	    public static Object[][] getDataFromCsv(String filePath) throws IOException {
	        List<Object[]> dataList = new ArrayList<>();
	        
	        try (FileReader reader = new FileReader(filePath);
	        		
	             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

	            for (CSVRecord record : csvParser) {
	                // Convert each record to an Object array
	                Object[] row = new Object[record.size()];
	                for (int i = 0; i < record.size(); i++) {
	                    row[i] = record.get(i);
	                }
	                dataList.add(row);
	            }
	        }
	        
	        // Convert List<Object[]> to Object[][]
	        Object[][] ad =  dataList.toArray(new Object[0][]);
	        return dataList.toArray(new Object[0][]);
	    }
	}
