package states;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlayState extends GameState {

	public PlayState(GameStateManager _gsm) {
		super(_gsm);
	}

	public void update() {
		
	}

	public void input() {
		
	}

	public void render(Graphics2D _g) {
		_g.setColor(Color.RED);
		_g.fillRect(0, 0, 64, 64);
	}
}
