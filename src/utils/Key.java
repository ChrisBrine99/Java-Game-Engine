package utils;

public class Key{
	public int keyBinding;
	public boolean curState;
	public boolean prevState;
	
	
	public Key(int _keyBinding) {
		keyBinding = _keyBinding;
		curState = false;
		prevState = false;
	}
}