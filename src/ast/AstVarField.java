package ast;

public class AstVarField extends AstVar {
    public AstVar  var;        // base object, e.g. o
    public String  fieldName;  // field name, e.g. y

    public AstVarField(AstVar var, String fieldName) {
        this.var        = var;
        this.fieldName  = fieldName;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        // Label: "Field Var o" if base is a simple ID, else just "Field Var"
        String label = "Field Var";
        if (var instanceof AstVarSimple) {
            AstVarSimple s = (AstVarSimple)var;
            label = "Field Var " + s.name;
        }

        gv.logNode(serialNumber, label);

        // If base is more complex than a simple ID, draw its subtree
        if (!(var instanceof AstVarSimple) && var != null) {
            var.printMe();
            gv.logEdge(serialNumber, var.serialNumber);
        }

        // Always draw a leaf for the field name: ID(y)
        int fieldSerial = AstNodeSerialNumber.getFresh();
        gv.logNode(fieldSerial, "ID(" + fieldName + ")");
        gv.logEdge(serialNumber, fieldSerial);
    }
}
