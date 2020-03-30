package FutureTransaction.ReportingSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Service {
	public Service() {
		
	}
	final Logger logger = Logger.getLogger(Service.class.getName());
	
	List<InputRecord> inputRecordList = new ArrayList();
	
	BufferedReader csvReader = null;
	String inputLine = null;
	public List<InputRecord> readFile(String inputFilePath) throws Exception{
		try{
			csvReader = new BufferedReader(new FileReader(inputFilePath));
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "Unexpected Error Occured "+e.toString());
		}
		while ((inputLine = csvReader.readLine())!=null){
			InputRecord record = new InputRecord();
			
			record.setClientId(inputLine.substring(4,19));
			record.setProductId(inputLine.substring(26, 45));
			record.setNetTransactionAmount(inputLine.substring(53,62),inputLine.substring(64,73));
			
			inputRecordList.add(record);
		}
		return inputRecordList;
		
	}
	
	public Map<String, Map<String, Integer>> process(List<InputRecord> inputRecordList){
		Map<String, Map<String, Integer>> resultMap = 
			    inputRecordList.stream().collect(Collectors.groupingBy(InputRecord :: getClientId, Collectors.groupingBy(InputRecord :: getProductId, Collectors.summingInt(InputRecord :: getNetTranactionAmount))));
		return resultMap;
	}

	public void writeFile(String outputFilePath, Map<String, Map<String, Integer>> InputFile){
		
		PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(outputFilePath));
        } catch (FileNotFoundException e) {
        	logger.log(Level.SEVERE, "Unexpected Error Occured "+e.toString());
        }
        StringBuilder builder = new StringBuilder();
        String columnNamesList = "ClientId"+"\t"+"ProductId"+"\t"+"NetTransactionAmount";
        
        builder.append(columnNamesList +"\n");
        pw.write(builder.toString());
        builder.setLength(0);
        Collection<Map<String, Integer>> innerMap = InputFile.values();
        for(String innerKey : InputFile.keySet()){
         Map<String, Integer> innerValue = InputFile.get(innerKey);
         for(String keyOne : innerValue.keySet()){
        	 builder.append(innerKey+ "\t"+ keyOne+"\t"+innerValue.get(keyOne)+"\n");
         	 pw.write(builder.toString());
         	 builder.setLength(0);
         }
        }   
        pw.close();
	}
}
	
	

	
