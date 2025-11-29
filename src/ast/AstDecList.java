package ast;

// Linked list of declarations, used for: program's top-level decs, class fields (cFieldList)
public class AstDecList extends AstNode {
    // Attributes:
    public AstDec head;
    public AstDecList tail;

    // Constructor:
    public AstDecList(AstDec head, AstDecList tail) {
        serialNumber = AstNodeSerialNumber.getFresh(); // allocates a serial number
        // printing:
        if (tail != null)
            System.out.print("====================== decList -> dec decList\n");
        else
            System.out.print("====================== decList -> dec\n");
        // storing the attributes:
        this.head = head;
        this.tail = tail;
    }

    // Methods:
    public void printMe() {
        if (head != null) {
            head.printMe();
        }
        if (tail != null) {
            tail.printMe();
        }
    }

}
