package mas.v4.arraylist.notfinish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class testArray {
	public static void main (String[] args){
		testArray t = new testArray();
		t.testArray();
	}
	
	public void testArray() {
/*		
		Map<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<2; i++) {
			list.add(new ArrayList<String>());
			list.get(i).add("AAA");
			map.put(null, list);
			System.out.println(map.get(null).get(i).get(0));
		}
*/		
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		list.add(new ArrayList<String>());
//		list.add(new ArrayList<String>());
//		list.add(new ArrayList<String>());
	
//		list.get(0).add("01");
//		list.get(0).add("02");
//		list.get(0).add("03");
		

//		list.get(1).add("11");
//		list.get(1).add("12");
//		list.get(1).add("13");
		
//		list.get(2).add("21");
//		list.get(2).add("22");		
//		list.get(2).add("23");
		
		
		int size1 = list.size();
		int size2 = list.get(0).size();
		
		System.out.println(size1);
		System.out.println(size2);
		
//		list.get(1)
		
		
		for (int i=0; i<size1; i++) {
			if (list.get(i).get(0) == "01") {
				System.out.println(list.get(i).get(0));
			}
		}
		
	}
	
	
 }
