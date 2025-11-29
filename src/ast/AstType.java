package ast;

// AST node representing a type name (int, string, void, or class/array name)
public class AstType extends AstNode {
    // Attributes:
    public String name;

    // Constructor:
    public AstType(String name) {
        serialNumber = AstNodeSerialNumber.getFresh(); // getting a new serial number
        // storing:
        this.name = name;
        // printing:
        System.out.format("====================== type -> %s\n", name);
    }

    // Methods:
    public void printMe() {
        AstGraphviz.getInstance().logNode(serialNumber,String.format("Type\n%s", cleanId(name)));
    }
}
