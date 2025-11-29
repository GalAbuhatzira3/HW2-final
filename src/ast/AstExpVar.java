package ast;

public class AstExpVar extends AstExp {
    public AstVar var;

    public AstExpVar(AstVar var) {
        /******************************/
        /* DON'T create a new node id */
        /******************************/
        this.var = var;
        this.serialNumber = var.serialNumber;

        System.out.print("====================== exp -> var\n");
    }

    @Override
    public void printMe() {
        /**********************************************
         * Do NOT log an extra "EXP VAR" node here.
         * Just delegate printing to the underlying var.
         **********************************************/
        if (var != null) {
            var.printMe();
        }
    }
}
