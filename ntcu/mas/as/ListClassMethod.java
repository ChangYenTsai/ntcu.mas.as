package ntcu.mas.as;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;
import analyzing.java.code.DirExplorer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ListClassMethod {
	
	private static String listClassMethod = null;
	private static FileWriter fwriter;
	private static List<String> storeListMethod = new ArrayList<String>();
//	private static int i = 1;
	
	public static void listClasses(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			storeListMethod.add("Path: "+path);
			
			String[] tokens = path.split("/");
			for (String token: tokens) {
				if (token.contains(".java")) {
					storeListMethod.add("Class: "+token);
				}
			}
//			File saveFile = new File("D:\\Eclipse\\Eclipse_Neon\\workspace\\ntcu.mas.as\\ListClassMethod\\"+listClassMethod+".java");

//			File saveFile = new File("D:\\Eclipse\\Eclipse_Neon\\workspace\\ntcu.mas.as\\ListClassMethod\\"+i+".java");
//			i++;
	            try {
	                new VoidVisitorAdapter<Object>() {
	                    @Override
//	                    public void visit(MethodDeclaration n, Object arg) {
//	                        super.visit(n, arg);
//	                        System.out.println(" * " + n.getName());
//	                    }	                    
	                    public void visit(MethodCallExpr n, Object arg) {
	                        super.visit(n, arg);
	                        System.out.println(" * " + n.getName());
	                        storeListMethod.add(n.getName());
	                    }
	                }.visit(JavaParser.parse(file), null);
	                System.out.println(); // empty line
	                storeListMethod.add("\n");
	            } catch (ParseException | IOException e) {
	                new RuntimeException(e);
	            }      		
	        }).explore(projectDir);
		outputListMethod(storeListMethod);
	    }

	
	public static void outputListMethod(List<String> storeListMethod)
	  {
		  File saveFile = new File("ListClassMethod\\ListClassMethod.java");
		  try
		  {
			  fwriter = new FileWriter(saveFile);
			  for (String str: storeListMethod) {
				  fwriter.write(str);
				  fwriter.append("\n");			  
			  }			  
			  fwriter.close();
//			  System.out.println("Done!");
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }
	
	
	public static void main(String[] args) {
		File projectDir = new File("src_1.8.1");	        
		listClasses(projectDir);        
	}
}