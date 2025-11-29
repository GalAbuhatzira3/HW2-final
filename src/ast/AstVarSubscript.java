package ast;

public class AstVarSubscript extends AstVar
{
	public AstVar var;
	public AstExp subscript;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstVarSubscript(AstVar var, AstExp subscript)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== var -> var [ exp ]\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.var = var;
		this.subscript = subscript;
	}

	/*****************************************************/
	/* The printing message for a subscript var AST node */
	/*****************************************************/
	public void printMe(){
		System.out.println("AST NODE SUBSCRIPT VAR");

		if (var != null) var.printMe();
		if (subscript != null) subscript.printMe();

		AstGraphviz.getInstance().logNode(
			serialNumber,
			"Array"
		); // or "Subscript", whatever matches the slides best

		if (var != null) {
			AstGraphviz.getInstance().logEdge(serialNumber, var.serialNumber);
		}
		if (subscript != null) {
			AstGraphviz.getInstance().logEdge(serialNumber, subscript.serialNumber);
		}
	}

}
