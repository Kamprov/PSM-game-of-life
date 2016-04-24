import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game implements KeyListener {
	private static Game instance;
	private static boolean board[][];
	private static boolean nextBoard[][];
	private static int dim;
	public static File resultFile;
	public static int iterationCounter = 1;
	

	private Game(String fileName, int sizeOfBoard) {
		dim = sizeOfBoard;
		board = readFromFile(fileName);
		resultFile = new File("Result.txt");
		nextBoard = readFromFile(fileName);
	}

	public static void init(String fileName, int dim) {
		if (instance == null)
			instance = new Game(fileName, dim);
		runGame();

	}

	private static boolean[][] readFromFile(String fileName) {
		File myFile = new File(fileName);
		boolean[][] newBoard = new boolean[dim][dim];
		int counter = 0;
		try {
			Scanner sc = new Scanner(myFile);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				line = line.replaceAll(" ", "");
				for (int i = 0; i < dim; i++) {
					if (line.charAt(i) == '-')
						newBoard[counter][i] = false;
					else if (line.charAt(i) == 'O')
						newBoard[counter][i] = true;
				}
				counter++;
			}
		} catch (FileNotFoundException e) {
			new JOptionPane().showMessageDialog(null, "File not found!");
		}
		return newBoard;
	}

	private static void writeToFile(boolean tab[][]) {

		String content = "";

		content += "Iteration number" + iterationCounter + "\n";
		for (int i = 0; i < tab.length; i++) {
			
			
			for (int j = 0; j < tab[i].length; j++) {

				if (tab[i][j] == true)
					content += "O";
				else if (tab[i][j] == false)
					content += "-";

				content += " ";
			}
			content += "\n";
		}
		
		content += "\n";

		try (FileOutputStream fop = new FileOutputStream(resultFile, true)) {
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean[][] nextIter() {

		return null;
	}

	private static void runGame() {
		int x=6;
		while (x > 0) {
			writeToFile(board);
			System.out.println("Iteration numbe: "+iterationCounter);
			drawConsoleBoard(board);
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == true) {
						willBeDead(i, j);
					} else {
						willBeAlive(i, j);
					}

				}

			}
			board = nextBoard;

			nextBoard = new boolean[dim][dim];
			
			iterationCounter++;
			x--;
		}

	}

	private static void drawConsoleBoard(boolean[][] board) {

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == true)
					System.out.print("O");
				else if (board[i][j] == false)
					System.out.print("-");

				System.out.print(" ");
			}

			System.out.println();
		}
	}

	private static void willBeDead(int i, int j) {
		int counter = 0;

		if ((i > 0) && (board[i - 1][j] == true))
			counter++;
		if (j > 0 && board[i][j - 1] == true)
			counter++;
		if (((i > 0) && (j > 0)) && board[i - 1][j - 1] == true)
			counter++;
		if (i < dim - 1 && board[i + 1][j] == true)
			counter++;
		if (j < dim - 1 && board[i][j + 1] == true)
			counter++;
		if (((i < dim - 1) && (j < dim - 1)) && board[i + 1][j + 1] == true)
			counter++;
		if (((i < dim - 1) && (j > 0)) && board[i + 1][j - 1] == true)
			counter++;
		if ((i > 0 && j < dim - 1) && board[i - 1][j + 1] == true)
			counter++;
		// if(i==3 &&j==3)
		// System.out.println("dead: " +counter);

		if (counter < 2 || counter > 3)
			nextBoard[i][j] = false;
		else
			nextBoard[i][j] = board[i][j];

	}

	private static void willBeAlive(int i, int j) {

		int counter = 0;
		if ((i > 0) && (board[i - 1][j] == true))
			counter++;
		if (j > 0 && board[i][j - 1] == true)
			counter++;
		if (((i > 0) && (j > 0)) && board[i - 1][j - 1] == true)
			counter++;
		if (i < dim - 1 && board[i + 1][j] == true)
			counter++;
		if (j < dim - 1 && board[i][j + 1] == true)
			counter++;
		if (((i < dim - 1) && (j < dim - 1)) && board[i + 1][j + 1] == true)
			counter++;
		if (((i < dim - 1) && (j > 0)) && board[i + 1][j - 1] == true)
			counter++;
		if ((i > 0 && j < dim - 1) && board[i - 1][j + 1] == true)
			counter++;
		// System.out.println("alive: "+counter);

		if (counter == 3) 
			nextBoard[i][j] = true;
			
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyChar() == 'm')
			runGame();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
