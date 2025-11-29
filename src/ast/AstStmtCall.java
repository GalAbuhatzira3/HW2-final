package ast;

public class AstStmtCall extends AstStmt
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstExp call;  // the call expression as an expression node

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AstStmtCall(AstExp call)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== stmt -> callExp ;\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.call = call;
    }

    /***************/
    /* PRINT ME    */
    /***************/
    public void printMe()
    {
        /*****************************/
        /* AST NODE TYPE = CALL STMT */
        /*****************************/
        System.out.print("AST NODE CALL STMT\n");

        /**********************/
        /* RECURSIVELY PRINT  */
        /**********************/
        if (call != null) call.printMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                "CALL\nSTMT");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (call != null)
            AstGraphviz.getInstance().logEdge(serialNumber, call.serialNumber);
    }
}
