package ast;

public class AstExpNewArray extends AstExp {
    public AstType elemType; // e.g. "int"
    public AstExp size;      // e.g. INT(10)

    public AstExpNewArray(AstType elemType, AstExp size) {
        this.elemType = elemType;
        this.size = size;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();
        gv.logNode(serialNumber, "New Array");

        if (elemType != null) {
            elemType.printMe();
            gv.logEdge(serialNumber, elemType.serialNumber);
        }
        if (size != null) {
            size.printMe();
            gv.logEdge(serialNumber, size.serialNumber);
        }
    }
}
