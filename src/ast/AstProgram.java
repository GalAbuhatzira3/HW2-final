package ast;

public class AstProgram extends AstNode {
    public AstDecList decList;

    public AstProgram(AstDecList decList) {
        this.decList = decList;
        serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        // Hide the PROGRAM node and just print the declarations
        if (decList != null) {
            decList.printMe();
        }
    }
}
