package ast;

public class AstStmtReturn extends AstStmt {
    public AstExp exp; // may be null for "return;"

    public AstStmtReturn(AstExp exp) {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.println("====================== stmt -> RETURN");
        this.exp = exp;
    }

    public void printMe() {
        System.out.println("AST NODE RETURN");

        if (exp != null) exp.printMe();

        AstGraphviz.getInstance().logNode(
            serialNumber,
            "Return"
        );

        if (exp != null) AstGraphviz.getInstance().logEdge(serialNumber, exp.serialNumber);
    }
}
