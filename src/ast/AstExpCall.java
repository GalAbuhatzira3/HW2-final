package ast;

public class AstExpCall extends AstExp {
    public AstVar func;        // the function name/receiver (simple var, field, etc.)
    public AstExpList args;    // can be null

    public AstExpCall(AstVar func, AstExpList args) {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.println("====================== exp -> callExp");
        this.func = func;
        this.args = args;
    }

    public void printMe() {
        System.out.println("AST NODE CALL");

        if (func != null) func.printMe();
        if (args != null) args.printMe();

        AstGraphviz.getInstance().logNode(
            serialNumber,
            "Call"
        );

        if (func != null) AstGraphviz.getInstance().logEdge(serialNumber, func.serialNumber);
        if (args != null) AstGraphviz.getInstance().logEdge(serialNumber, args.serialNumber);
    }
}
