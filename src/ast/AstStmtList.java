package ast;

public class AstStmtList extends AstNode
{
    public AstStmt head;
    public AstStmtList tail;

    public AstStmtList(AstStmt head, AstStmtList tail)
    {
        serialNumber = AstNodeSerialNumber.getFresh();

        if (tail != null) System.out.print("====================== stmts -> stmt stmts\n");
        else              System.out.print("====================== stmts -> stmt      \n");

        this.head = head;
        this.tail = tail;
    }

    @Override
    public void printMe()
    {
        System.out.print("AST NODE STMT LIST\n");

        AstGraphviz gv = AstGraphviz.getInstance();

        // one node for the whole list
        gv.logNode(
            serialNumber,
            "Statements");

        // walk the linked list, print each stmt, and connect it
        AstStmtList curr = this;
        while (curr != null)
        {
            if (curr.head != null)
            {
                curr.head.printMe();                     // print the statement subtree
                gv.logEdge(serialNumber, curr.head.serialNumber); // connect from SAME Statements node
            }
            curr = curr.tail;
        }
    }
}
