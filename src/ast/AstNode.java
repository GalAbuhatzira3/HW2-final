package ast;

public abstract class AstNode
{
	/*******************************************/
	/* The serial number is for debug purposes */
	/* In particular, it can help in creating  */
	/* a graphviz dot format of the AST ...    */
	/*******************************************/
	public int serialNumber;
	
	/***********************************************/
	/* The default message for an unknown AST node */
	/***********************************************/
	public void printMe(){
		System.out.print("AST NODE UNKNOWN\n");
	}
	
    protected String cleanId(String s) {
        if (s == null) return null;
        String res = s;
        while (res.startsWith("ID(") && res.endsWith(")")) {
            res = res.substring(3, res.length() - 1);
        }
        return res;
    }
	protected String cleanStringToken(String raw) {
		if (raw == null) return null;
		String s = raw.trim();

		if (s.startsWith("STRING(") && s.endsWith(")")) {
			s = s.substring("STRING(".length(), s.length() - 1);
		}

		if (s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
			s = s.substring(1, s.length() - 1);
		}

		return s;  
	}		
}
