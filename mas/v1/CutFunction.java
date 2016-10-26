package mas.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CutFunction {
	
	  public static void main(String args[])
	  {
		  	FileInputStream in = null;
			try {
				in = new FileInputStream("5c85c44601c38541572b756406edbc8d08d11fe0.java");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));

		    String strLine;
		    String[] filearray;
		    filearray = new String[20];

		    try {
				while ((strLine = br.readLine()) != null) {

				for (int j = 0; j < filearray.length; j++){
				filearray[j] = br.readLine();
				}

				}
				
				System.out.println(Arrays.toString(filearray));				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
}
