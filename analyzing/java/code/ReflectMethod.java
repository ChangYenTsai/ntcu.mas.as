package analyzing.java.code;

import java.lang.reflect.Method;

import japa.parser.JavaParser;
import mas.v3.ListClassMethod;
import mas.v3.UI;

public class ReflectMethod {
	public static void main(String[] args) {
		Class temp3 = japa.parser.JavaParser.class;
		Class temp2 = UI.class;
		Class temp = ListClassMethod.class;
	    Class aClass = String.class;

	    // Get the methods
	    Method[] methods = temp2.getDeclaredMethods();

	    // Loop through the methods and print out their names
	    for (Method method : methods) {
	    	
	      System.out.println(method.getName());
	    }
	  }
}
