package ast;

public class AstParam extends AstNode {
    public AstType type;
    public String  name;

    public AstParam(AstType type, String name) {
        this.type = type;
        this.name = name;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    // small local helper so we don't depend on other classes
    private String stripId(String s) {
        if (s == null) return "";
        if (s.startsWith("ID(") && s.endsWith(")")) {
            return s.substring(3, s.length() - 1);
        }
        return s;
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        String pname = stripId(name);       // show just x, not ID(x)
        gv.logNode(serialNumber, "Param " + pname);

        if (type != null) {
            gv.logEdge(serialNumber, type.serialNumber);
            type.printMe();
        }
    }
}
