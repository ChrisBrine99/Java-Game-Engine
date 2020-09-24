package states;

import java.awt.Graphics2D;

public abstract class GameState {
	private GameStateManager gsm;
	
	public GameState(GameStateManager _gsm) {
		gsm = _gsm;
	}
	
	public abstract void update();
	public abstract void input();
	public abstract void render(Graphics2D _g);
}