package ast;

public class AstParamList extends AstNode {
    public AstParam     head;
    public AstParamList tail;

    public AstParamList(AstParam head, AstParamList tail) {
        this.head = head;
        this.tail = tail;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        // single ParamList node
        gv.logNode(serialNumber, "ParamList");

        // iterate the linked list and hang all params directly under THIS node
        AstParamList it = this;
        while (it != null) {
            if (it.head != null) {
                gv.logEdge(serialNumber, it.head.serialNumber);
                it.head.printMe();
            }
            it = it.tail;
        }
    }
}
