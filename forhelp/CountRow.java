package forhelp;

import java.io.*;

public class CountRow{
  public static void main( String args[] )
  {
              
  try{  
       BufferedReader in = new BufferedReader(new FileReader("1.8.1.txt"));
 
       String str;
       int num=0;
       while ((str = in.readLine()) != null) { //readLine()依序讀取檔案內的一行文字
    	   ++num;  //每讀一行，num就加1
       }
       System.out.print(num);  //print出file.txt內的行數
       in.close();
       
  }catch(Exception ext){
	  
  }
  
  }
 
}