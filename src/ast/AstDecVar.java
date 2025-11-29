package ast;

// Declaration of a global/local/field variable:
public class AstDecVar extends AstDec {
    // Attribute:
    public AstType type;
    public String  name;
    public AstExp  init; // may be null

    // Constructor:
    public AstDecVar(AstType type, String name, AstExp init) {
        serialNumber = AstNodeSerialNumber.getFresh(); // gets a new serial number
        // storing:
        this.type = type;
        this.name = name;
        this.init = init;
        // printing:
        if (init == null)
            System.out.format("====================== decVar -> type ID( %s ) ;\n", name);
        else
            System.out.format("====================== decVar -> type ID( %s ) := exp ;\n", name);
    }

    // Methods:
    public void printMe(){
        System.out.format("AST NODE DEC VAR( %s )\n", cleanId(name));
        // logs itself as e.g. DEC\nVAR\n(ID(name))
        AstGraphviz.getInstance().logNode(serialNumber,String.format("VarDec\n%s", cleanId(name)));
        // calls printMe functions:
        if (type != null) type.printMe();
        if (init != null) init.printMe();
        // logs edges to type and init:
        AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
        if (init != null) AstGraphviz.getInstance().logEdge(serialNumber, init.serialNumber);
    }
}
