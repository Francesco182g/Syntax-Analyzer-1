import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Parser {
	
	private static Scanner in;
	static int prt;
	public final static Logger log = Logger.getLogger(Lexer.class.getName());

	public static void main (String[] args) {
		String lessema = null;
		try {
			lessema = leggiFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Lexer lexer = new Lexer();
		lexer.getNextToken(lessema);
		prt = 0;
	}

	
	public static String leggiFile() throws FileNotFoundException {
	    File file= new File("C:\\Users\\Francesco\\Desktop\\Compilatori\\"+"Testo.txt");
	    in = new Scanner (file);
	    String lessema = "";
	    while (in.hasNext()) {
	      lessema += "" + in.nextLine();
	      lessema = lessema + "\n";
	    }
		return lessema;
	  }
	
/*	GRAMMATICA:
 * 
 * 	Program -> Stmt A
 * 	A -> ; Stmt A
 * 	A -> 3(empty)
 * 	Stmt -> IF Expr THEN Stmt
 * 	Stmt -> ID ASSIGN Expr
 * 	Expr -> Term RELOP Term
 * 	Expr -> Term
 * 	Term -> ID
 * 	Term -> NUMBER
 */

	public static void getNextToken(Token token) {
		Token to = token;
		
	}
	
	static boolean Program(){	
		
		return false;
	}
	
	 static boolean A(){
		return false;
	 }
	 
	 static boolean Stmt() {
		return false;
	 }
	 
	 static boolean Expr() {
		 return false;
	 }
	 
	 static boolean Term() {
		 return false;
	 }
	
	
}
