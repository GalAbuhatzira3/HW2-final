package ast;

public class AstDecClass extends AstDec {
    public String     name;
    public String     parentName;   // may be null
    public AstDecList fields;       // list of fields + methods

    public AstDecClass(String name, String parentName, AstDecList fields) {
        this.name        = name;
        this.parentName  = parentName;
        this.fields      = fields;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        // 1. Class label (keep whatever you had, just no "\n")
        String label;
        if (parentName == null) {
            label = "Class (" + cleanId(name) + ")";
        } else {
            label = "Class (" + cleanId(name) + ") extends (" + cleanId(parentName) + ")";
        }
        gv.logNode(serialNumber, label);

        // 2. Attach fields directly
        AstDecList it = fields;        // or whatever your field list is called
        while (it != null) {
            if (it.head != null) {
                gv.logEdge(serialNumber, it.head.serialNumber);
                it.head.printMe();     // <-- NOT it.printMe()
            }
            it = it.tail;
        }
    }


}
