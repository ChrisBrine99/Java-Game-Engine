package utils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyHandler {
	public static ArrayList<Key> keys = new ArrayList<Key>();
	
	// The indices for each key binding
	public static final int GAME_UP = 			0;
	public static final int GAME_DOWN = 		1;
	public static final int GAME_LEFT = 		2;
	public static final int GAME_RIGHT = 		3;
	
	// Instantiates all of the game's valid key inputs with their default values.
	public static void instantiateKeys() {
		keys.add(new Key(KeyEvent.VK_UP));
		keys.add(new Key(KeyEvent.VK_DOWN));
		keys.add(new Key(KeyEvent.VK_RIGHT));
		keys.add(new Key(KeyEvent.VK_LEFT));
	}
	
	// Updates the key's previous state before gathering input once again.
	public static void updatePrevState() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).prevState = keys.get(i).curState;
		}
	}
	
	// Sets the array index that matches the key code to the requested state, which can
	// either be true for being pressed, or false for being released.
	public static void keySet(int _keyCode, boolean _isPressed) {
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i).keyBinding == _keyCode) {
				keys.get(i).curState = _isPressed;
				break;
			}
		}
	}
	
	// Checks the key at that index and determines if it was pressed that frame or not.
	public static boolean isPressed(int _index) {
		if (_index >= 0 && _index < keys.size()) {
			return keys.get(_index).curState && !keys.get(_index).prevState;
		}
		return false;
	}
	
	// Checks the key at that index and determines if it has been held down.
	public static boolean isHeld(int _index) {
		if (_index >= 0 && _index < keys.size()) {
			return keys.get(_index).curState && keys.get(_index).prevState;
		}
		return false;
	}
	
	// Checks the key at the index and determines if it was just released on the current frame.
	public static boolean isReleased(int _index) {
		if (_index >= 0 && _index < keys.size()) {
			return !keys.get(_index).curState && keys.get(_index).prevState;
		}
		return false;
	}
}
