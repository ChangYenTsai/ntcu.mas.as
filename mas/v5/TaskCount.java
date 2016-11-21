package mas.v5;

public class TaskCount extends UI{
	
	public void countLocation() {
		
		//to count the location number of specified method
		for (int cM=0; cM<countMethod; cM++){
			int count=1;
			
			for (int cL=1; cL<storeMethod.get(cM).size(); cL++){
				count++;
			}
			
			int sol = count-1;					
			resultOutput.add(new MethodLocation(storeMethod.get(cM).get(0), sol));
		}
	}
	
}
