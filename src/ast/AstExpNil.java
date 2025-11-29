package ast;

public class AstExpNil extends AstExp {
    public AstExpNil() {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.println("====================== exp -> NIL");
    }

    public void printMe() {
        System.out.println("AST NODE NIL");

        AstGraphviz.getInstance().logNode(
            serialNumber,
            "Nil"
        );
    }
}
