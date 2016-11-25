package mas.v5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

public class TaskOutput extends UI{	
	private ArrayList<MethodLocation> resultOutput = new ArrayList<MethodLocation>();
	
	public StringBuilder countAndOutput(ArrayList<ArrayList<String>> storeMethod) {

		//to count the location number of specified method
		for (int cM=0; cM<countMethod; cM++){
			int count=1;
			
			for (int cL=1; cL<storeMethod.get(cM).size(); cL++){
				count++;
			}
			
			int sol = count-1;					
			resultOutput.add(new MethodLocation(storeMethod.get(cM).get(0), sol));
		}
		System.out.println("Counting done.");
				
		//sort number descending
		Collections.sort(resultOutput, MethodLocation.MethodSort);		
		
		//output how many result
		int outputNumber = 30;
		for (int i=0; i<outputNumber; i++) {
			System.out.println(resultOutput.get(i));
			storeResultOutput.append(resultOutput.get(i).toString() + "\n");
			
			//to find resultOutput's method in storeWholeSentence's method and match
			for (int j=0; j<storeWholeSentence.size()-1; j++) {
				if (resultOutput.get(i).toString().substring(resultOutput.get(i).toString().indexOf(":")+1, resultOutput.get(i).toString().indexOf(" "))
					.equals(storeWholeSentence.get(j).get(0).toString().substring(0, storeWholeSentence.get(j).get(0).toString().indexOf("(")))) {
					
					Map<String, Integer> map = new HashMap<String, Integer>();
					
					//count different sentence's value
					for (int m=1; m<storeWholeSentence.get(j).size(); m++) {
						Integer count = map.get(storeWholeSentence.get(j).get(m));
						map.put(storeWholeSentence.get(j).get(m), (count == null) ? 1 : count + 1);
/*						
						int countSentence=0;
						for (int n=1; n<storeWholeSentence.get(j).size(); n++) {
							if (storeWholeSentence.get(j).get(m).equals(storeWholeSentence.get(j).get(n))) {
								countSentence++;
							}
						}
*/						
//						System.out.println("Sentence: " + storeWholeSentence.get(j).get(m) + " Value: " + countSentence);
				
					}
					
					//output format
					for (Map.Entry<String, Integer> entry : map.entrySet()) {
						if (entry.getValue() > 10) {
							System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
							storeResultOutput.append("Key: " + entry.getKey() + " Value: " + entry.getValue() + "\n");
						}
					}				
				}
			}
			storeResultOutput.append("\n");
		}
		
		System.out.println("Output " + outputNumber + " results.");
		return storeResultOutput;
	}
	
}
