package ast;

public class AstStmtVarDec extends AstStmt {
    public AstDecVar dec;  // type, name, init

    public AstStmtVarDec(AstDecVar dec) {
        this.dec = dec;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        // First, recursively print the real subtree nodes
        if (dec.type != null) {
            dec.type.printMe();        // will log "Type int/string/..."
        }
        if (dec.init != null) {
            dec.init.printMe();        // expression subtree
        }

        // Now log this statement node itself
        gv.logNode(serialNumber, "Assign");

        // 1. left child: Type ...
        if (dec.type != null) {
            gv.logEdge(serialNumber, dec.type.serialNumber);
        }

        // 2. middle child: Var <name> (pretty, without ID(...))
        int varSerial = AstNodeSerialNumber.getFresh();
        String niceName = cleanId(dec.name);      // "ID(a)" -> "a"
        gv.logNode(varSerial, "Var " + niceName);
        gv.logEdge(serialNumber, varSerial);

        // 3. right child: initializer, if present
        if (dec.init != null) {
            gv.logEdge(serialNumber, dec.init.serialNumber);
        }
    }
}
