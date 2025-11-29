package ast;

public class AstVarSimple extends AstVar {
    public String name;

    public AstVarSimple(String name) {
        this.name = name;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();
        String nice = cleanId(name);  

        gv.logNode(serialNumber, "Var " + nice);

    }
}
