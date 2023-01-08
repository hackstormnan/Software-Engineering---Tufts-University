import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Chess layout moves");
		}

		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());

		// args[0] is the layout file name
		// args[1] is the moves file name
		// Put your code to read the layout file and moves files
		// here.

		try {
			File myObj = new File("layout1");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.indexOf('#') != -1) {
					continue;
				}
				String loc = data.substring(0, 2);
				String piece_name = data.substring(3, 5);
				Piece piece = Piece.createPiece(piece_name);
				Board.theBoard().addPiece(piece, loc);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			File myObj = new File("moves1");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.indexOf('#') != -1) {
					continue;
				}
				String start_loc = data.substring(0, 2);
				String end_loc = data.substring(3, 5);
				Board.theBoard().movePiece(start_loc, end_loc);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Leave the following code at the end of the simulation:
		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
    }
}