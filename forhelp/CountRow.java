package forhelp;

import java.io.*;

public class CountRow{
  public static void main( String args[] )
  {
              
  try{  
       BufferedReader in = new BufferedReader(new FileReader("1.8.1.txt"));
 
       String str;
       int num=0;
       while ((str = in.readLine()) != null) { //readLine()�̧�Ū���ɮפ����@���r
    	   ++num;  //�CŪ�@��Anum�N�[1
       }
       System.out.print(num);  //print�Xfile.txt�������
       in.close();
       
  }catch(Exception ext){
	  
  }
  
  }
 
}