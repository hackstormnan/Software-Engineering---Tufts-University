import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    public static void test1() {
		Board b = Board.theBoard();
		b.registerListener(new Logger());

		Piece.registerPiece(new PawnFactory());
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());

		Piece p1 = Piece.createPiece("wk");
		Piece p2 = Piece.createPiece("bk");
		Piece p3 = Piece.createPiece("wq");
		Piece p4 = Piece.createPiece("bq");
		Piece p5 = Piece.createPiece("wn");
		Piece p6 = Piece.createPiece("bn");
		Piece p7 = Piece.createPiece("wr");
		Piece p8 = Piece.createPiece("br");
		Piece p9 = Piece.createPiece("wb");
		Piece p10 = Piece.createPiece("bb");
		Piece p11 = Piece.createPiece("wp");
		Piece p12 = Piece.createPiece("bp");

		//king
//		b.addPiece(p1, "e2");
//		b.addPiece(p11, "f3");
//		b.movePiece("e2", "f3");


		//queen
//		b.addPiece(p3, "e2");
//
//		b.addPiece(p12, "e1");
//		b.addPiece(p12, "e3");
//		b.addPiece(p12, "d2");
//		b.addPiece(p12, "f2");
//		b.addPiece(p12, "d1");
//		b.addPiece(p12, "d3");
//		b.addPiece(p12, "f1");
//		b.addPiece(p11, "f3");
//		b.addPiece(p12, "h5");

//		b.movePiece("e2", "e1");
//		b.movePiece("e2", "e3");
//		b.movePiece("e2", "d2");
//		b.movePiece("e2", "f2");
//		b.movePiece("e2", "d1");
//		b.movePiece("e2", "d3");
//		b.movePiece("e2", "f1");
//		b.movePiece("e2", "h5");

		//knight
//		b.addPiece(p5, "d4");

//		b.addPiece(p7, "c6");
//		b.addPiece(p6, "e6");
//		b.addPiece(p6, "c2");
//		b.addPiece(p6, "e2");
//		b.addPiece(p6, "b5");
//		b.addPiece(p6, "b3");
//		b.addPiece(p6, "f5");
//		b.addPiece(p6, "f3");

//		b.movePiece("d4", "c2");


		//rook
//		b.addPiece(p7, "d4");
//
//		b.addPiece(p7, "d7");
//		b.addPiece(p8, "d8");
//		b.movePiece("d4", "d8");


//		b.addPiece(p8, "d1");
//		b.movePiece("d4", "d1");
//
//		b.addPiece(p8, "a4");
//		b.movePiece("d4", "a4");
//
//		b.addPiece(p8, "h4");
//		b.movePiece("d4", "h4");

		//bishop
//		b.addPiece(p9, "d5");

//		b.addPiece(p10, "b7");
//		b.movePiece("d5", "c6");
//		b.movePiece("c6", "b7");

//		b.addPiece(p10, "b3");
//		b.movePiece("d5", "b3");

//		b.addPiece(p10, "f7");
//		b.movePiece("d5", "f7");

//		b.addPiece(p10, "f3");
//		b.movePiece("d5", "f3");

//		b.addPiece(p11, "e2");
//		b.movePiece("e2", "e3");
//		b.movePiece("e2", "e4");
//		b.movePiece("e2", "e1");
//		b.addPiece(p12, "d3");
//		b.addPiece(p12, "f3");

//		b.movePiece("e2", "d3");
//		b.movePiece("e2", "f3");


//		b.addPiece(p12, "e4");
//		b.movePiece("e4", "e3");
//		b.movePiece("e4", "e2");

//		b.addPiece(p11, "e2");
//		b.movePiece("e4", "e2");
//		b.addPiece(p11, "d3");
//		b.movePiece("e4", "d3");
//		b.movePiece("d3", "d4");








//		Piece p13 = Piece.createPiece("s");
//		System.out.println(p13.color);


//		System.out.println(p1.color);
//		System.out.println(p2.color);
//		System.out.println(p3.color);
//		System.out.println(p4.color);
//		System.out.println(p5.color);
//		System.out.println(p6.color);
//		System.out.println(p7.color);
//		System.out.println(p8.color);
//		System.out.println(p9.color);
//		System.out.println(p10.color);
//		System.out.println(p11.color);
//		System.out.println(p12.color);
	}
    
    public static void main(String[] args) {
		test1();
    }

}