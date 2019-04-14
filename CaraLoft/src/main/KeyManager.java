package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager(){
        keys = new boolean[256]; // this keys array stores whether a key is being pressed or not
    }

    public void tick(){ // checks and updates based on whether the key is being pressed
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
    }

    @Override
    public void keyPressed(KeyEvent e) { // called whenever a key is pressed
        keys[e.getKeyCode()] = true; // gets the keycode and sets it to true in the array
    }

    @Override
    public void keyReleased(KeyEvent e) { // called whenever a key is released
        keys[e.getKeyCode()] = false; // we can now tell if it is being pressed or not
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
