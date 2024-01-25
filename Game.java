import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

//simple game interface
public class Game extends JFrame implements KeyListener{
	
	private PlayerCharacter player;
	
	//setting the fixed values:
	private static final Dimension frameDimension = new Dimension(600, 600);
	private static final Color backgroundColor = new Color(0, 0, 0);
	private static final Color playerColor = new Color(57, 255, 20);
	
	public Game(String playerSize, String playerSwiftness){	
		
		//setting the JFrame dimension by setting the content pane and letting the frame adjust
		this.getContentPane().setPreferredSize(frameDimension);
		this.pack();
	
		this.addKeyListener(this);
		
		//null layout to allow component placement flexibility and precision
		this.setLayout(null);
		
		this.getContentPane().setBackground(backgroundColor);
		
		//instantiating a new player (sub of panel)
		this.player = new PlayerCharacter(playerSize, playerColor, playerSwiftness);
		
		//defining player's dimension and position
		int playerWidth = this.player.getWidth();
		int playerHeight = this.player.getHeight();
		
		this.player.setBounds((int)(frameDimension.getWidth() - playerWidth) / 2,
							 ((int)frameDimension.getHeight() - playerHeight) / 2, 
							  playerWidth, playerHeight);
		
		this.add(this.player);
		
		this.setVisible(true);
	}
	
	//move the "character" in 2 dimensional framework using handcrafted vector data structure
	public void movePlayer(Vectors.Vector2Int movementVector) {
		
		//mechanism: getting the player rectangle bound and "moving" the x and y coordinate
		Rectangle playerBounds = this.player.getBounds();
		
		int playerWidth = (int)playerBounds.getWidth();
		int playerHeight = (int)playerBounds.getHeight();
		int playerXPos = (int)playerBounds.getX();
		int playerYPos = (int)playerBounds.getY();
		
		int translatedPlayerXPos = playerXPos + movementVector.x;
		int translatedPlayerYPos = playerYPos - movementVector.y;
		
		Rectangle translatedPlayerBounds = new Rectangle(translatedPlayerXPos,
														 translatedPlayerYPos,
														 playerWidth, playerHeight);
		
		this.player.setBounds(translatedPlayerBounds);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	//invoked when within the context of JFrame, any key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		
		/*this method is specialized only for moving the player by detecting key press
		 * and "translating" it to movement vector, which is used to move the player*/
		
		/*initializing movement vector, whose value x and y will vary according
		 * to which key is pressed (WASD movement system)*/
		Vectors.Vector2Int movement;
		
		//getting the information about which key is pressed
		int key = e.getKeyCode();
		
		//translating key into movement vector
		switch (key) {
			case KeyEvent.VK_W:
				movement = new Vectors.Vector2Int(0, this.player.speed);
				break;
			case KeyEvent.VK_S:
				movement = new Vectors.Vector2Int(0, -this.player.speed);
				break;
			case KeyEvent.VK_A:
				movement = new Vectors.Vector2Int(-this.player.speed, 0);
				break;
			case KeyEvent.VK_D:
				movement = new Vectors.Vector2Int(this.player.speed, 0);
				break;
			default:
				movement = new Vectors.Vector2Int(0, 0);
		}
		
		//using the movement vector to move the player
		this.movePlayer(movement);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}

//player character is represented by an extended panel, modified according to arguments given
class PlayerCharacter extends JPanel{
	
	//scaling the player size
	private double scale;

	private Color color;
	
	//influence how a single movePlayer method translates the player
	public int speed;
	
	public PlayerCharacter(String size, Color color, String swiftness) {
		
		//converting ordinal data type 'size' to discretal 'scale'
		switch (size) {
			case "Enormous":
				this.scale = 2;
				break;
			case "Large":
				this.scale = 1.5;
				break;
			case "Medium":
				this.scale = 1;
				break;
			case "Small":
				this.scale = 0.7;
				break;
			default:
				this.scale = 1;
		}		
		
		this.color = color;
		
		//converting ordinal data type 'swiftness' to discretal 'speed'
		switch (swiftness) {
			case "Lightning":
				this.speed = 8;
				break;
			case "Fast":
				this.speed = 6;
				break;
			case "Medium":
				this.speed = 3;
				break;
			case "Slow":
				this.speed = 1;
				break;
			default:
				this.speed = 3;
		}
		
		this.ConfigurePlayer();
	}
	private void ConfigurePlayer() {
		
		//configuring the panel by scaling and setting the color
		Dimension defaultPanelSize = new Dimension(50, 50);
		Dimension scaledPanelSize = Utility.scaleDimension(defaultPanelSize, this.scale);
		
		this.setSize(scaledPanelSize);
		this.setBackground(this.color);
	}
}
