package ast;

// Function/method declaration:
public class AstDecFunc extends AstDec {
    // Attributes:
    public AstType returnType;
    public String name;
    public AstParamList params; // may be null
    public AstStmtList body; // may be null (but in this language it exists)

    // Constructor:
    public AstDecFunc(AstType retType, String name, AstParamList params, AstStmtList body) {
        serialNumber = AstNodeSerialNumber.getFresh(); // getting a new serial number
        // storing:
        this.returnType = retType;
        this.name    = name;
        this.params  = params;
        this.body    = body;
        // printing:
        System.out.format("====================== decFunc -> type ID( %s ) (...) { stmtList }\n", name);
    }

    // Methods:
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        String fName = cleanId(name);
        gv.logNode(serialNumber, "Function " + fName);

        if (returnType != null)
            gv.logEdge(serialNumber, returnType.serialNumber);

        if (params != null)
            gv.logEdge(serialNumber, params.serialNumber);

        if (body != null)
            gv.logEdge(serialNumber, body.serialNumber);

        if (returnType != null) returnType.printMe();
        if (params != null)     params.printMe();
        if (body != null)       body.printMe();
    }

}
