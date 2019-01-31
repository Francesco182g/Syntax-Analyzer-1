//Created by Francesco Garofalo
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Parser {
	//Log per Info
	public final static Logger log = Logger.getLogger(Parser.class.getName());
	//Scanner InputFile
	private static Scanner in;
	//Puntatore
	public static int pt = 1;
	//Array di Token
	public static ArrayList<Token> tokens = new ArrayList<>();
	//Token
	private static Token token;
	//State
	private static int state;
	//Istanza del lexer
	private static Lexer lexer;


	public static void main (String[] args) {
		String lessema = "";
		try {
			lessema = leggiFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		lexer = new Lexer(lessema);
		verificaInput();
	}

	/*
	 * Leggi File()
	 * Read input
	 * return lessemi on String
	 */
	public static String leggiFile() throws FileNotFoundException {
		File file= new File("C:\\Users\\Francesco\\Desktop\\Compilatori\\"+"Esercitazione3.txt");
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
	 * 	Case 0: Program -> Stmt Program1
	 * 	Case 1: Program1 -> ; Stmt Program1
	 * 	Case 2: Program1 -> £(empty)
	 * 	Case 3: Stmt -> IF Expr THEN Stmt
	 * 	Case 4: Stmt -> ID ASSIGN Expr
	 * 	Case 5: Expr -> Term Expr1
	 *	Case 6: Expr1 -> RELOP term
	 *	Case 7: Expr1 -> £
	 * 	Case 8: Term -> ID
	 * 	Case 9: Term -> NUMBER
	 * 
	 * Program = P, Stmt = S, Expr = E, Term = T
	 */


	/*
	 * Verify if the input respect the grammar with boolean var
	 * if Verify is true: Grammar is respected
	 * else Grammar is not respected 
	 */
	public static void verificaInput() {
		System.out.println("Start Syntax ->>>>>>> ");
		Program();

	}

	//PROGRAM
	public static boolean Program() {
		int backtrack = 0;
		boolean check = false;
		tokens.add(new Token("head", "head"));
		getNextToken();
		System.out.println("Program -> Stmt Program1");
		if(infoState() == true) {
			if(S() == true) {
				if(P1() == true) {
					check = true;
				}
			}
		}else {
			check = false;
		}


		System.out.println("Program: "+check);
		return check;
	}


	public static boolean P1() {
		boolean check = true;

		if(infoState() == true) {
			System.out.println("Program1 -> ; Stmt Program1");
			if(SEMI() == true) {
				if(S() == true) {
					if(P1() == true) {
						check = true;
					}else {
						pt--;
						check = false;
					}
				}else {
					pt--;
				}
			}
		}else {
			check = false;
		}

		return check;
	}


	public static boolean S() {
		boolean check = false;
		//System.out.println("Puntatore prima dell'if: "+pt);
		if(infoState() == true) {
			System.out.println("Stmt -> IF Expr THEN Stmt");
			if(IF() == true) {
				if(E() == true) {
					if(THEN() == true) {
						if(S() == true) {
							check = true;
						} else {
							pt = pt - 2;
							check = false;
						}						
					}
				}else {
					check = false;
					pt--;

				}
			}
			//System.out.println("Puntatore prima dopo l'if: "+pt);
			System.out.println("Stmt -> ID ASSIGN Expr");
			if(ID() == true) {
				if(Assign() == true) {
					if(E() == true) {
						check = true;
					}else {
						pt = pt -2;
						check = false;
					}
				}else{
					pt--;
					check = false;
				}
			}
		}
		return check;
	}


	//EXPR
	public static boolean E() {	
		boolean check = false;
		System.out.println("Expr -> Term Expr1");
		if(T()==true && E1()==true) {
			check = true;
		}
		return check;
	}


	//EXPR1
	public static boolean E1() {
		boolean check = true;
		if(infoState() == true) {
			System.out.println("Expr1 -> RELOP term");
			if(tokens.get(pt).getId().equals("Relop")) {
				pt++;
				if(T() == true) {
					check = true;
				} else {
					pt--;
				}
			}		
		}
		return check;
	}


	//TERM
	public static boolean T() {
		boolean check = false;
		if(infoState() == true) {

		System.out.println("Term -> ID");
		if(tokens.get(pt).getId().equals("ID")) {
			check = true;
		}	
		System.out.println("Term -> NUMBER");
		if(tokens.get(pt).getId().equals("Nconst")) {
			check = true;
		}
		if(check == true) {
			pt++;
		}
		}
		return check;
	}


	//IF
	public static Boolean IF() {
		boolean check = false;
		if(infoState() == true) {
			System.out.println("IF");
			if(tokens.get(pt).getAttribute().equals("IF")) {
				check = true;
			}
		}
		if(check == true) {
			pt++;
		}	
		return check;
	}

	//ID
	public static Boolean ID() {
		boolean check = false;
		if(infoState() == true) {
			System.out.println("ID");
			if(tokens.get(pt).getId().equals("ID")) {
				check = true;
			}	
			if(check == true) {
				pt++;
			}
		}
		return check;
	}

	//ASSIGN
	public static Boolean Assign() {
		boolean check = false;
		if(infoState() == true) {
			System.out.println("ASSIGN");
			if(tokens.get(pt).getAttribute().equals("ASSIGN")) {
				check = true;
			}	
			if(check == true) {
				pt++;
			}
		}
		return check;
	}

	//THEN
	public static Boolean THEN() {
		boolean check = false;
		if(infoState() == true) {
			System.out.println("THEN");
			if(tokens.get(pt).getAttribute().equals("THEN")) {
				check = true;
			}
			if(check == true) {
				pt++;
			}
		}
		return check;
	}

	//SEMI
	public static Boolean SEMI() {
		boolean check = false;
		if(infoState() == true) {
			System.out.println(";");
			if(tokens.get(pt).getAttribute().equals(";")) {
				check = true;
			}
			if(check == true) {
				pt++;
			}
		}
		return check;
	}

	public static Boolean infoState() {
		boolean status;
		//System.out.println(pt +" "+ (tokens.size()-1));
		if(pt == tokens.size()) {
			status = getNextToken();
		} else {
			status = true;
		}
		return status;
	}

	public static boolean getNextToken() {
		Token token = lexer.getNextToken();
		tokens.add(token);
		if(token.getAttribute().equals("ENDFILE")) {
			return false;
		} else {
			//System.out.println("Callnext token: "+token.toString());
			return true;
		}
	}

}