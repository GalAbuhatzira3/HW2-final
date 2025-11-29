package ast;

public class AstExpList extends AstNode {
    public AstExp head;
    public AstExpList tail;

    public AstExpList(AstExp head, AstExpList tail) {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.head = head;
        this.tail = tail;
    }

    public void printMe() {
        System.out.println("AST NODE EXP LIST");
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "ExpList");
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}
