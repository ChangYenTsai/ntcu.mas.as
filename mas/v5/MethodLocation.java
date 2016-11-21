package mas.v5;

import java.util.Comparator;

public class MethodLocation {
	private String methodOutput;
	private int locationCount;
	
	public MethodLocation(String methodOutput, int locationCount) {
		this.methodOutput = methodOutput;
		this.locationCount = locationCount;
	}
	
	public static Comparator<MethodLocation> MethodSort = new Comparator<MethodLocation>() {
		public int compare(MethodLocation s1, MethodLocation s2) {

			   int count1 = s1.getlocationCount();
			   int count2 = s2.getlocationCount();

			   /*For ascending order*/
//			   return count1-count2;

			   /*For descending order*/
			   return count2-count1;
	}};
	
	@Override
    public String toString() {
        return "(M:" + methodOutput + " L:" + locationCount + ")";
    }
	
	public int getlocationCount() {
		return locationCount;
	}
	
	
	
}
