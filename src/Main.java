import java.io.*;
import java_cup.runtime.Symbol;
import ast.*;

public class Main {
    public static void main(String[] argv) {
        // Expecting 2 arguments: input file path, output file path
        if (argv.length < 2)
            return;
        // Extract the names
        String inputFileName  = argv[0]; // input file path
        String outputFileName = argv[1];  // output file path

        try {
            FileReader  fileReader = new FileReader(inputFileName);
            PrintWriter fileWriter = new PrintWriter(outputFileName);
            // creating the lexer:
            Lexer  l = new Lexer(fileReader);
            // creating the parser:
            Parser p = new Parser(l, fileWriter);
            // run the parser and get the result symbol
            Symbol parseResult = p.parse();
            // visualization:
            try {
                if (parseResult != null && parseResult.value instanceof AstNode) {
                    AstNode root = (AstNode) parseResult.value;
                    // this will recursively log nodes/edges into AstGraphviz
                    root.printMe();
                }
                // finalize DOT file even if root is null or cast fails
                AstGraphviz.getInstance().finalizeFile();
            } catch (Exception ignored) {
                // don't let debug stuff break the required behavior
            }
            // if we got here, parsing succeeded: no lexical/syntax error
            fileWriter.print("OK");
            fileWriter.close();
        }
        catch (Exception e){
            try {
                PrintWriter fileWriter = new PrintWriter(outputFileName);
                fileWriter.print("ERROR");
                fileWriter.close();
            } catch (Exception ignored) {}
        }
    }
}
