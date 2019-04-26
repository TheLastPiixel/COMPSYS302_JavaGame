package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{

	private boolean[] ActivatedKeys;
	public boolean W, A, S, D;
	public boolean One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
	public boolean PageDown;
	public boolean Space;
	public boolean Esc;
	public boolean P;
	public boolean I;
	public boolean E;
	public boolean Q;
	
	public KeyboardInput() {
		ActivatedKeys = new boolean[256];
	}
	
	public void Tick() {
		W = ActivatedKeys[KeyEvent.VK_W];
		A = ActivatedKeys[KeyEvent.VK_A];
		S = ActivatedKeys[KeyEvent.VK_S];
		D = ActivatedKeys[KeyEvent.VK_D];
		PageDown = ActivatedKeys[KeyEvent.VK_PAGE_DOWN];
		Space = ActivatedKeys[KeyEvent.VK_SPACE];
		Esc = ActivatedKeys[KeyEvent.VK_ESCAPE];
		P = ActivatedKeys[KeyEvent.VK_P];
		I = ActivatedKeys[KeyEvent.VK_I];
		E = ActivatedKeys[KeyEvent.VK_E];
		Q = ActivatedKeys[KeyEvent.VK_Q];
		One = ActivatedKeys[KeyEvent.VK_1];
		Two = ActivatedKeys[KeyEvent.VK_2];
		Three = ActivatedKeys[KeyEvent.VK_3];
		Four = ActivatedKeys[KeyEvent.VK_4];
		Five = ActivatedKeys[KeyEvent.VK_5];
		Six = ActivatedKeys[KeyEvent.VK_6];
		Seven = ActivatedKeys[KeyEvent.VK_7];
		Eight = ActivatedKeys[KeyEvent.VK_8];
		Nine = ActivatedKeys[KeyEvent.VK_9];
		Zero = ActivatedKeys[KeyEvent.VK_0];
	}
	
	@Override
	public void keyPressed(KeyEvent Event) {
		//Records which keys are pressed
		ActivatedKeys[Event.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent Event) {
		//Records which keys are released
		ActivatedKeys[Event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent Event) {
		
	}
	
}
