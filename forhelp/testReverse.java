package forhelp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testReverse {
	
	private String ch = null;
	
	
	public static void main(String[] args) {
		testReverse r = new testReverse();
		r.reverse();
	}
	
	public void reverse() {
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("e22fab96158f44d63d5260bf83afe8fc19bac6b7.java"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		List<String> tmp = new ArrayList<String>();

	    do {
	        try {
				ch = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        tmp.add(ch);

	    } while (ch != null);

	    for(int i=tmp.size()-1;i>=0;i--) {
	        System.out.println(tmp.get(i));
	    }				
	}	
}
