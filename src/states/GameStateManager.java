package states;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> states;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		states.add(new PlayState(this));
	}
	
	public void update() {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).update();
		}
	}
	
	public void input() {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).input();
		}
	}
	
	public void render(Graphics2D _g) {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).render(_g);
		}
	}
}
