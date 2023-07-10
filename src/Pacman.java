import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Pacman extends Character implements KeyListener  {

private static final long serialVersionUID = 1L;

	public Pacman(double startingPointX, double startingPointY)
	{
		super(startingPointX,startingPointY);
		setHeroLoc(startingPointX,startingPointY);
		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus(true);
		
		timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == timer)
					move();
			}
		});

		timer.start();
	}
	@Override
	public void move(){
			super.move();
			setHeroLoc(this.x,this.y);
			repaint();
	}

	public void setMoveUp(){
		deltaX=0; deltaY=-1;
	}

	public void setMoveDown(){
		deltaX=0; deltaY=1;
	}

	public void setMoveRight(){
		deltaX=1; deltaY=0;
	}

	public void setMoveLeft(){
		deltaX=-1; deltaY=0;
	}

	public void setXindex(double x){
		this.x=x;
	}
	
	public void setYindex(double y){
		this.y=y;
	}

	public void zeroDeltaXY(){
		this.deltaX=0;
		this.deltaY=0;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT){
			image = leftIcone();
			setMoveLeft();
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT){
			image = rightIcone();
			setMoveRight();
		}
		if (e.getKeyCode()==KeyEvent.VK_UP){
			image = upIcone();
			setMoveUp();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN){
			image = downIcone();
			setMoveDown();
		}
	}
	@Override
	public ImageIcon leftIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/"  +"PacmanLeft.png"));
	}
	public ImageIcon rightIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanRight.png"));
	}
	public ImageIcon upIcone() {
		return new ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanUp.png"));
	}
	public ImageIcon downIcone() {
		return new  ImageIcon(getClass().getClassLoader().getResource("img/" +"PacmanDown.png"));
	}
	@Override
	public void startingPoint(double d, double e) {
		image = leftIcone();
		super.startingPoint(d, e);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
