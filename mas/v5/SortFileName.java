package mas.v5;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class SortFileName extends UI{
/*	
	public int sort() {
		
		File[] endFile = null;
		endFile = new File(fcf2 + "\\endFile").listFiles();
		
		Arrays.sort(endFile, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int n1 = extractNumber(o1.getName());
                int n2 = extractNumber(o2.getName());
                return n2 - n1;
            }
            private int extractNumber(String name) {
                int i = 0;
                try {
                    int s = name.indexOf('_')+1;
                    int e = name.lastIndexOf('.');
                    String number = name.substring(s, e);
                    i = Integer.parseInt(number);
                } catch(Exception e) {
                    i = 0; // if filename does not match the format
                           // then default to 0
                }
                return i;
            }
        });
	}
*/
}
