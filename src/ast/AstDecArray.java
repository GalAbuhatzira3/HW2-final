package ast;

public class AstDecArray extends AstDec
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public String  name;
    public AstType type;

    /******************/
    /* CONSTRUCTOR(S) */
    /******************/
    public AstDecArray(String name, AstType type)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format(
            "====================== dec -> ARRAY ID( %s ) = type[] ;\n",
            name);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.type = type;
    }

    /***************/
    /* PRINT ME    */
    /***************/
    public void printMe()
    {
        /*************************************/
        /* AST NODE TYPE = ARRAY DECLARATION */
        /*************************************/
        System.out.format("AST NODE ARRAY DEC( %s )\n", name);

        /**********************/
        /* RECURSIVELY PRINT  */
        /**********************/
        if (type != null) type.printMe();

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AstGraphviz.getInstance().logNode(
            serialNumber,
            String.format("ARRAY\nDEC\n(%s)", name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (type != null)
            AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
    }
}
