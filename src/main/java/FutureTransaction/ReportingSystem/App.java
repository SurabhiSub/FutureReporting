package FutureTransaction.ReportingSystem;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App 
{
    public static void main( String[] args )
    {
    	final Logger logger = Logger.getLogger(App.class.getName());
    	String inputFilePath = null;
    	String outputFilePath = null;
    	Service app = new Service();
       	
    	try{
    		inputFilePath = args[0];
    		outputFilePath = args[1];
    	}
    	catch(Exception e){
    		logger.log(Level.SEVERE, "The Input and Output file paths need to be specified as program arguments. Please specify and re-run the program. ");
    	}
    	
    	try{
    		List<InputRecord> inputFile = app.readFile(inputFilePath);
        	Map<String, Map<String, Integer>> resultMap = app.process(inputFile);
        	app.writeFile(outputFilePath, resultMap);
        }
        catch(Exception e){
        	logger.log(java.util.logging.Level.SEVERE, "Unexpected Error Occured!"+e.toString());
        }
        
    }
}
