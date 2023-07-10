import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private Pacman pacman;
	GameFrame gameFrame;
	private Ghost[] ghostSet;
	private JPanel background;
	private JPanel itemsBoard;
	private JLayeredPane multiBoard;
	private int mapHeight;
	private int mapWidth;
	private double squareHeight;
	private double squareWidth;
	int score;
	JPanel p;
	JLabel lbl1;
	int lives;
	final int W=1;
	final int F=2;
	final int E=3; 
	private int board[][] = {
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{F,F,F,F,F,F,F,F,F,F,W,E,E,E,E,E,E,W,F,F,F,F,F,F,F,F,F,F},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}
	};

	
	public Board(GameFrame thisGame) throws IOException{
		gameFrame=thisGame;
		ghostSet =new Ghost[4];
		score=0;
		lives=3;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		mapHeight=413;
		mapWidth=422;
		this.setBackground(Color.black);
		squareHeight=mapHeight/31;
		squareWidth=mapWidth/28;
		multiBoard = new JLayeredPane();
		multiBoard.setPreferredSize(new Dimension(mapHeight, mapWidth));
		JPanel background = createBackground();
		background.setBounds(0, 0, mapWidth, mapHeight);
		multiBoard.add(background, Integer.valueOf(0));
		CreateItemBoard();
		itemsBoard.setOpaque(false);
		itemsBoard.setSize(mapWidth, mapHeight);
		itemsBoard.setBackground(null);
		multiBoard.add(itemsBoard, Integer.valueOf(1));
		pacman = placePacman();
		pacman.setOpaque(false);
		pacman.setSize(mapWidth, mapHeight);
		multiBoard.add(pacman,Integer.valueOf(3));
		pacman.setFocusable(true);
		pacman.setBoard(this);
		
		CreateStatusBar();
		add(p);
		
		ghostSet[0]=new RedGhost(12*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[1]=new BlueGhost(13*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[2]=new PinkGhost(14*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostSet[3]=new OrangeGhost(15*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		
		for(int i = 0; i<4;i++){
			ghostSet[i].setOpaque(false);
			ghostSet[i].setSize(mapWidth, mapHeight);
			multiBoard.add(ghostSet[i], Integer.valueOf(4));
			ghostSet[i].setBoard(this);
		}
		multiBoard.setVisible(true);
		this.add(multiBoard);
	}

	private void CreateStatusBar(){
		try 
		{
			lbl1 = new JLabel("Label", JLabel.LEFT);
		    lbl1.setBackground(Color.BLUE);
			lbl1.setText("Rezultat: "+score);
			Font f = new Font("Broadway", Font.BOLD, 24);
		    lbl1.setFont(f);
		    p = new JPanel(){
			private static final long serialVersionUID = 1L;
			private Image pac = ImageIO.read(getClass().getClassLoader().getResource("img/"+"Heart.png"));
			@Override
			public void paint(Graphics g){
				super.paint(g);
					for(int i = 0;i<lives;i++)
						g.drawImage(pac, 29*i, 1, null);}
			};
			 p.setLayout(new FlowLayout()); 
			 p.add(lbl1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void CreateItemBoard(){
		try {
			itemsBoard = new JPanel(){
				
				private static final long serialVersionUID = 1L;
				private Image food =  ImageIO.read(getClass().getClassLoader().getResource("img/"+"Dot.png"));
				@Override
				public void paint (Graphics g){
					super.paint(g);
					for (int i=0; i<board.length; i++)
						for (int j=0; j<board[i].length; j++) {
							if(board[i][j]==F)
								g.drawImage(food, (int)(j*squareWidth), (int)(i*(squareHeight)+squareHeight/2), null);
						}
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JPanel createBackground(){
		try {
			background = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image map = ImageIO.read(getClass().getClassLoader().getResource("img/"+"Map.png"));	
				@Override
				public void paint(Graphics g) { 
					super.paint(g);
					g.drawImage(map, 0, 0, null);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setVisible(true);
		background.setBackground(Color.black);
		return background;
	}

	public Pacman placePacman(){
		return new Pacman(13*squareWidth+squareWidth/2, 22.5*(squareHeight)+squareHeight/2);
	}

	public Ghost placeGhost(){
		return new Ghost(12*squareWidth+squareWidth/2, 13.5*(squareHeight)+squareHeight/2);
	}
	
	public int getMapWidth(){
		return mapWidth;
	}

	public int getMapHeight(){
		return mapHeight;
	}
	@Override
	public String toString(){
		String s="";
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++)
				s=s+","+board[i][j];
			s=s+"\n";
		}
		return s;
	}

	public void addItemToMultiBoard(Component comp, Integer num){
		multiBoard.add(comp,num);
	}

	public boolean isClear(int i, int j) {
			return !(board[i][j]==1);
	}

	public void eat(int i, int j) {
		if(board[i][j]==2){
		   board[i][j]=3;
		   score++;
		   if(score>1)
			   lbl1.setText("Rezultat: " +score);
		}
		if (score==300){
			winGameEvent();
		}
	}

	private void winGameEvent() {
		
		stopAllCharacters();
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Da";
		options[1] = "Ne";
		int result = JOptionPane.showOptionDialog(frame.getContentPane(), "Pokusaj ponovo?", "Pobedili ste i osvojili 300 poena!", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		switch (result){
		case (0):
			try {
				this.removeAll();
				gameFrame.createBoard();				
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			case (1):
			System.exit(0);
			break;
		}
	}
	public void restart() {
		lives--;
		p.getRootPane().validate();
		p.getRootPane().repaint();
		pacman.startingPoint(13*squareWidth+squareWidth/2,22.5*(squareHeight)+squareHeight/2);
		for(int i=0; i<4;i++){
			ghostSet[i].startingPoint((12+i)*squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
			ghostSet[i].rounds=0;
			ghostSet[i].startProcess=false;
		}
		if (lives==0){
			endGameEvent();
		}
	}
	private void endGameEvent() {
		
		stopAllCharacters();
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Da";
		options[1] = "Ne";
		int result = JOptionPane.showOptionDialog(frame.getContentPane(), "Pokusaj ponovo?", "Izgubili ste!", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		switch (result){
		case (0):
			try {
				this.removeAll();
				gameFrame.createBoard();				
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		case (1):
			System.exit(0);
			break;
		}
	}

	private void stopAllCharacters() {
		pacman.stopTimer();
		for(int i =0;i<4;i++)
		{
			ghostSet[i].stopTimer();
		}
	}

}
