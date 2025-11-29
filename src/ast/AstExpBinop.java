package ast;

public class AstExpBinop extends AstExp
{
	int op;
	public AstExp left;
	public AstExp right;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstExpBinop(AstExp left, AstExp right, int op)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> exp BINOP exp\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	/*************************************************/
	/* The printing message for a binop exp AST node */
	/*************************************************/
    /***************/
    /* PRINT ME    */
    /***************/
    public void printMe()
    {
        /**********************************/
        /* AST NODE TYPE = BINOP EXP      */
        /**********************************/
        System.out.print("AST NODE BINOP\n");

        /**********************/
        /* RECURSIVELY PRINT  */
        /**********************/
        if (left  != null) left.printMe();
        if (right != null) right.printMe();

        /**************/
        /* OP STRING  */
        /**************/
        String opStr;
        switch (op)
        {
            case 0:  opStr = "+";  break;
            case 1:  opStr = "-";  break;
            case 2:  opStr = "*";  break;
            case 3:  opStr = "/";  break;
            case 4:  opStr = "<";  break;
            case 5:  opStr = ">";  break;
            case 6:  opStr = "=="; break;
            default: opStr = "?";  break;
        }

        /**********************************/
        /* PRINT to AST GRAPHVIZ DOT file */
        /**********************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("BINOP\n%s", opStr));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (left  != null) AstGraphviz.getInstance().logEdge(serialNumber, left.serialNumber);
        if (right != null) AstGraphviz.getInstance().logEdge(serialNumber, right.serialNumber);
    }

}
