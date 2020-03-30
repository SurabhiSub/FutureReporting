package FutureTransaction.ReportingSystem;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;



public class ServiceTest  {

	List<InputRecord> ilist = new ArrayList<>();
	@Before
	public void setUp() throws Exception {
		InputRecord i1 = new InputRecord("c1", "p1", 10);
		InputRecord i2 = new InputRecord("c1", "p1", 20);
		InputRecord i3 = new InputRecord("c1", "p1", 30);
		InputRecord i4 = new InputRecord("c2", "p1", 8);
		InputRecord i5 = new InputRecord("c3", "p1", 25);
		ilist.add(i1);
		ilist.add(i2);
		ilist.add(i3);
		ilist.add(i4);
		ilist.add(i5);
		
		}
	
	@Test
	public void processTest(){
		Service service = new Service();
		Map<String, Map<String, Integer>> result = service.process(ilist);
		
		Map<String,String> res = new HashMap<String, String>();
		List<String> slist = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for(String innerKey : result.keySet()){
	         Map<String, Integer> innerValue = result.get(innerKey);
	       
	         for(String keyOne : innerValue.keySet()){
	        	 builder.setLength(0);
	        	 String s = builder.append(innerKey+ "\t"+ keyOne+"\t"+innerValue.get(keyOne)+"\n").toString();
	        	 slist.add(s);
	        	 String[] str = s.split("\t");
	        	 res.put(innerKey+keyOne, str[2]);
	        }
		}
		assertEquals(res.get("c1p1").trim(),"60".trim());
		assertEquals(res.get("c2p1").trim(),"8".trim());
		assertEquals(res.get("c3p1").trim(),"25".trim());
	}

}
