package mas.v1;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class ParserTool {
	public static void main(String[] args) throws Exception
    {
        FileInputStream in = new FileInputStream("e22fab96158f44d63d5260bf83afe8fc19bac6b7.java");

        CompilationUnit cu;
        try
        {
            cu = JavaParser.parse(in);
        }
        finally
        {
            in.close();
        }
        new MethodVisitor().visit(cu, null);
    }

    private static class MethodVisitor extends VoidVisitorAdapter
    {
        @Override
        public void visit(MethodCallExpr methodCall, Object arg)
        {
            System.out.print("Method call: " + methodCall.getName() + "\n");
            List<Expression> args = methodCall.getArgs();
            if (args != null)
                handleExpressions(args);
        }

        private void handleExpressions(List<Expression> expressions)
        {
            for (Expression expr : expressions)
            {
                if (expr instanceof MethodCallExpr)
                    visit((MethodCallExpr) expr, null);
                else if (expr instanceof BinaryExpr)
                {
                    BinaryExpr binExpr = (BinaryExpr)expr;
                    handleExpressions(Arrays.asList(binExpr.getLeft(), binExpr.getRight()));
                }
            }
        }
    }
}
