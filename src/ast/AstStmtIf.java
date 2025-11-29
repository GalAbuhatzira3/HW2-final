package ast;

public class AstStmtIf extends AstStmt
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstExp      cond;
    public AstStmtList thenBody;
    public AstStmtList elseBody; // may be null

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AstStmtIf(AstExp cond, AstStmtList body)
    {
        this(cond, body, null);
    }

    // IF with optional ELSE
    public AstStmtIf(AstExp cond, AstStmtList thenBody, AstStmtList elseBody)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (elseBody == null)
        {
            System.out.print(
                "====================== stmt -> IF (exp) { stmtList }\n");
        }
        else
        {
            System.out.print(
                "====================== stmt -> IF (exp) { stmtList } ELSE { stmtList }\n");
        }

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.cond     = cond;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    /***************/
    /* PRINT ME    */
    /***************/
    public void printMe()
    {
        /**************************/
        /* AST NODE TYPE = IF STMT*/
        /**************************/
        System.out.print("AST NODE IF STMT\n");

        /**********************/
        /* RECURSIVELY PRINT  */
        /**********************/
        if (cond     != null) cond.printMe();
        if (thenBody != null) thenBody.printMe();
        if (elseBody != null) elseBody.printMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                "IF");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (cond     != null) AstGraphviz.getInstance().logEdge(serialNumber, cond.serialNumber);
        if (thenBody != null) AstGraphviz.getInstance().logEdge(serialNumber, thenBody.serialNumber);
        if (elseBody != null) AstGraphviz.getInstance().logEdge(serialNumber, elseBody.serialNumber);
    }
}
