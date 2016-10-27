package forhelp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hdgf.streams.Stream;

public class ReadOneLineAndDownload {
	
	private static ReadOneLineAndDownload rd;

	public static void main( String args[] ) {


		// Open the file
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("1.6.4.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine = null;
		
		try {
			strLine = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Read File Line By Line
		int i = 1;
		while (strLine != null){
			System.out.println(strLine);		
			try
			  {
			   String strUrl = "https://github.com/apache/ant/commit/"+ strLine + ".patch";
			   URL source = new URL(strUrl);
			   String theStrDestDir = "D:\\Eclipse\\Eclipse_Neon\\workspace\\ntcu.mas.as\\ant_versions\\1.6.4\\";
			   File theStockDest = new File(theStrDestDir);
			   FileUtils.forceMkdir(theStockDest);
			   
//			   File destination = new File(theStrDestDir + strLine + ".java");
			   File destination = new File(theStrDestDir + "commit_"+ i + ".java");
			   i++;
			   FileUtils.copyURLToFile(source, destination);
			   //File file = new File(".");
			   System.out.println("File Downloaded!");
/*			   
			   try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/			   		   
			  } catch (MalformedURLException e)
			  {
			   e.printStackTrace();
			  } catch (IOException e)
			  {
			   e.printStackTrace();
			  }
			try {
				strLine = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
/*		
		try {
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  System.out.println (strLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Close the input stream
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/				
	}
}
