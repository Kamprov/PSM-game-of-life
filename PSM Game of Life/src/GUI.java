import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.util.List;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class GUI extends JFrame {
//
//	private JButton buttonNextIter, buttonRun;
//	private JMenuBar menu;
//	private JPanel panelGame, panelMain, panelEast;
//	JFileChooser fileChooser = new JFileChooser();
//	// private JPane paneGame = new JPane();
//
//	int width = 10;
//	int height = 10;
//	int rows = 10;
//	int cols = 10;
//
//	int xOffset;
//	int yOffset;
//
//	private List<Rectangle> cells;
//	private Point selectedCell;
//
//	public void init() {
//
//	}
//
//	public GUI() {
//		// construct preComponents
//		panelMain = new JPanel(new BorderLayout());
//		panelEast = new JPanel();
//		panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
//		panelGame = new JPanel();
//
//		JMenu fileMenu = new JMenu("File");
//		JMenuItem load_fileItem = new JMenuItem("Load file");
//		fileMenu.add(load_fileItem);
//		JMenuItem exitItem = new JMenuItem("Exit");
//		fileMenu.add(exitItem);
//		JMenu helpMenu = new JMenu("Help");
//		JMenuItem aboutItem = new JMenuItem("About");
//		helpMenu.add(aboutItem);
//
//		// construct components
//		buttonNextIter = new JButton("Next iteration");
//		buttonRun = new JButton("Run");
//		menu = new JMenuBar();
//		menu.add(fileMenu);
//		menu.add(helpMenu);
//
//		// adjust size
//		setPreferredSize(new Dimension(800, 500));
//		// setLayout(null);
//		buttonNextIter.setSize(new Dimension(20, 30));
//		buttonNextIter.setAlignmentX(Component.CENTER_ALIGNMENT);
//		// panelEast.add(Box.createRigidArea(new Dimension(0, 150)));
//		// panelEast.add(Box.createVerticalGlue());
//		buttonRun.setSize(new Dimension(20, 30));
//		buttonRun.setAlignmentX(Component.CENTER_ALIGNMENT);
//		// buttonNextIter.add(Box.createRigidArea(new Dimension(50, 0)));
//
//		// add components
//		panelEast.add(Box.createRigidArea(new Dimension(0, 150)));
//		panelEast.add(buttonNextIter);
//		panelEast.add(Box.createRigidArea(new Dimension(0, 30)));
//		// panelEast.add(Box.createVerticalGlue());
//		panelEast.add(buttonRun);
//		panelMain.add(menu, BorderLayout.NORTH);
//		panelMain.add(panelEast, BorderLayout.EAST);
//
//		cells = new ArrayList<>(cols * rows);
//		panelMain.add(panelGame, BorderLayout.CENTER);
//
//		// set component bounds (only needed by Absolute Positioning)
//		// buttonNextIter.setBounds(10, 10, 10, 30);
//		// menu.setBounds(0, 0, 785, 25);
//
//		// listeners
//
//		fileMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// wczytywanie pliku
//			}
//		});
//
//		exitItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//			}
//		});
//
//		aboutItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "dupa dupa", "About",
//						JOptionPane.INFORMATION_MESSAGE);
//			}
//		});
//		//
//		MouseAdapter mouseHandler;
//		mouseHandler = new MouseAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				Point point = e.getPoint();
//
//				int width = getWidth();
//				int height = getHeight();
//
//				int cellWidth = width / cols;
//				int cellHeight = height / rows;
//
//				selectedCell = null;
//				if (e.getX() >= xOffset && e.getY() >= yOffset) {
//
//					int column = (e.getX() - xOffset) / cellWidth;
//					int row = (e.getY() - yOffset) / cellHeight;
//
//					if (column >= 0 && row >= 0 && column < cols && row < rows) {
//
//						selectedCell = new Point(column, row);
//
//					}
//
//				}
//				repaint();
//
//			}
//		};
//		addMouseMotionListener(mouseHandler);
//		//
//
//		setContentPane(panelMain);
//		this.setVisible(true);
//		setTitle("Game of Life");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		pack();
//	}
//
//	// @Override
//	// public void invalidate(){
//	// cells.clear();
//	// selectedCell = null;
//	// panelGame.invalidate();
//	// }
//
//	public void paint(Graphics g) {
//		panelGame.paint(g);
//		Graphics2D g2d = (Graphics2D) g.create();
//
//		int width = getWidth();
//		int height = getHeight();
//
//		int cellWidth = width / cols;
//		int cellHeight = height / rows;
//
//		xOffset = (width - (cols * cellWidth)) / 2;
//		yOffset = (height - (rows * cellHeight)) / 2;
//
//		if (cells.isEmpty()) {
//			for (int row = 0; row < rows; row++) {
//				for (int col = 0; col < cols; col++) {
//					Rectangle cell = new Rectangle(xOffset + (col * cellWidth),
//							yOffset + (row * cellHeight), cellWidth, cellHeight);
//					cells.add(cell);
//				}
//			}
//		}
//
//		if (selectedCell != null) {
//
//			int index = selectedCell.x + (selectedCell.y * cols);
//			Rectangle cell = cells.get(index);
//			g2d.setColor(Color.BLUE);
//			g2d.fill(cell);
//
//		}
//
//		g2d.setColor(Color.GRAY);
//		for (Rectangle cell : cells) {
//			g2d.draw(cell);
//		}
//
//		g2d.dispose();
//
//	}
//}

public class GUI extends JFrame implements WindowListener {
	JPanel panelMain;
	JButton buttonNext, buttonRun;

	public GUI() {
		panelMain = new JPanel();
		buttonNext = new JButton("Next");
		buttonRun = new JButton("Run");

		panelMain.add(buttonNext);
		// panelMain.add(buttonRun);

		// listeners

		buttonNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.runGame();
			}

		});

		buttonRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.setRunning();
				Game.runGame();
			}

		});
		//

		addWindowListener(this);
		setContentPane(panelMain);
		setVisible(true);
		pack();
		setSize(275, 75);
		setTitle("Game of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {

		System.out.println("ddd");

	}

	@Override
	public void windowClosing(WindowEvent e) {

		Game.checkLoops();

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
