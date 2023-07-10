import java.util.Random;

public class Ghost extends Character {
	
	private static final long serialVersionUID = 1L;
	public boolean startProcess;
	public Ghost(double startingPointX, double startingPointY) 
	{
		super(startingPointX, startingPointY);
		block=false;
		startProcess=false;
	}
	
	@Override
    public void move() 
    {
    	RandomMove();	
		if (eatPacMan())
		{
			this.board.restart();
			startProcess=false;
		}
		repaint();
	}

	private boolean eatPacMan() {
		return Math.sqrt(Math.pow(this.x-pacmanX, 2)+Math.pow(this.y-pacmanY,2))<15;
	}

	private void RandomMove() {
		if (!startProcess){
			deltaX=0; deltaY=-1;
			this.x=x+deltaX;
			this.y=y+deltaY;
			if(this.y==140){
				startProcess=true;
			}
				return;
		}
		if (block){
			block=false;
			Random Rnd1 = new Random();
			Random Rnd2 = new Random();

			int i = Rnd1.nextInt(900);
			int j = Rnd2.nextInt(900);

			i=i*j;
			i=i%4;
			switch(i){
			case 0:
				deltaX=0; deltaY=-1;
				break;
			case 1:
				deltaX=0; deltaY=1;
				break;
			case 2:
				deltaX=-1; deltaY=0;
				break;
			case 3:
				deltaX=1; deltaY=0;
				break;
			}
		}
		super.move();
	}

	@Override
	public void stopTimer() {
		super.stopTimer();
	}
	
}
