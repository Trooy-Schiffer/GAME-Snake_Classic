package com.thiago_souza.snake.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (!Game.getSnake().get(0).isDown()) {
				Game.getSnake().get(0).setUp(true);
				Game.getSnake().get(0).setDown(false);
				Game.getSnake().get(0).setLeft(false);
				Game.getSnake().get(0).setRight(false);
			}
			break;
		case KeyEvent.VK_S:
			if (!Game.getSnake().get(0).isUp()) {
				Game.getSnake().get(0).setDown(true);
				Game.getSnake().get(0).setUp(false);
				Game.getSnake().get(0).setLeft(false);
				Game.getSnake().get(0).setRight(false);
			}
			break;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			if (!Game.getSnake().get(0).isRight()) {
				Game.getSnake().get(0).setLeft(true);
				Game.getSnake().get(0).setUp(false);
				Game.getSnake().get(0).setDown(false);
				Game.getSnake().get(0).setRight(false);
			}
			break;
		case KeyEvent.VK_D:
			if (!Game.getSnake().get(0).isLeft()) {
				Game.getSnake().get(0).setRight(true);
				Game.getSnake().get(0).setUp(false);
				Game.getSnake().get(0).setDown(false);
				Game.getSnake().get(0).setLeft(false);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
