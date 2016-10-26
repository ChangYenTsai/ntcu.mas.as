package mas.v3;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;
import analyzing.java.code.DirExplorer;

import java.io.File;
import java.io.IOException;


public class ListClassMethod {
	public static void listClasses(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
	            try {
	                new VoidVisitorAdapter<Object>() {
	                    @Override
	                    public void visit(MethodDeclaration n, Object arg) {
	                        super.visit(n, arg);
	                        System.out.println(" * " + n.getName());
	                    }
	                }.visit(JavaParser.parse(file), null);
	                System.out.println(); // empty line
	            } catch (ParseException | IOException e) {
	                new RuntimeException(e);
	            }
	        }).explore(projectDir);
	    }

	    public static void main(String[] args) {
	        File projectDir = new File("src");
	        listClasses(projectDir);
	   }
}