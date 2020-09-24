package core;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window(String _title, int _width, int _height) {
		setTitle(_title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel(_width, _height));
		pack(); // Sets the frame size to the width and height
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
