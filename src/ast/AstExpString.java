package ast;

public class AstExpString extends AstExp {
    public String value;   // whatever the lexer put in s.value

    public AstExpString(String value) {
        this.value = value;
        this.serialNumber = AstNodeSerialNumber.getFresh();
    }

    @Override
    public void printMe() {
        AstGraphviz gv = AstGraphviz.getInstance();

        String s = cleanStringToken(value);  

        String label = "String \"" + s + "\"";

        gv.logNode(serialNumber, label);
    }
}
