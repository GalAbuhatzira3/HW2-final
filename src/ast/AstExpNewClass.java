package ast;

public class AstExpNewClass extends AstExp {
    public String className;
    public AstExpList args;   // may be null

    public AstExpNewClass(String className, AstExpList args) {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.format("====================== exp -> new %s(...)\n", cleanId(className));
        this.className = className;
        this.args = args;
    }

    public void printMe() {
        System.out.format("AST NODE NEW CLASS( %s )\n", cleanId(className));

        if (args != null) args.printMe();

        AstGraphviz.getInstance().logNode(
            serialNumber,
            String.format("New\nClass\n%s", cleanId(className))
        );

        if (args != null) AstGraphviz.getInstance().logEdge(serialNumber, args.serialNumber);
    }
}
