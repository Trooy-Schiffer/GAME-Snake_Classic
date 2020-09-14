package com.thiago_souza.snake.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.thiago_souza.snake.main.Game;

public class Snake extends Entity {

	private boolean up, down, right, left;
	private static double speed = 2.5;
	
	private static int score = -1;
	private static String scoreboard;
	
	public Snake(double x, double y, int width, int height) {
		super(x, y, width, height);
	}

	public void update() {
		snakeTail();
		
		// limit screen
		if (x + Game.DIMENSION < 0) {
			x = Game.WIDTH;
		} else if (x >= Game.WIDTH) {
			x = 0;
		}
		if (y + Game.DIMENSION < 0) {
			y = Game.HEIGHT;
		} else if (y >= Game.HEIGHT) {
			y = 0;
		}

		// moviment
		if (up) {
			y -= speed;
			collision();
		} else if (down) {
			y += speed;
			collision();
		}
		if (left) {
			x -= speed;
			collision();
		} else if (right) {
			x += speed;
			collision();
		}

		// collision apple
		if (new Rectangle((int) x, (int) y, Game.DIMENSION, Game.DIMENSION)
				.intersects(new Rectangle((int) Game.getApple().getX(), (int) Game.getApple().getY(), Game.DIMENSION, Game.DIMENSION))) {
			Game.getApple().setX(new Random().nextInt(Game.WIDTH - Game.DIMENSION));
			Game.getApple().setY(new Random().nextInt(Game.WIDTH - Game.DIMENSION));
			score++;
			speed += 0.1;
			for (int aux = 0; aux < 5; aux++) {
				Game.getSnake().add(new Snake(0, 0,Game.DIMENSION,Game.DIMENSION));
				snakeTail();
			}
		}
	}
	
	private void snakeTail() {
		for (int i = Game.getSnake().size() - 1; i > 0; i--) {
			Game.getSnake().get(i).x = Game.getSnake().get(i - 1).x;
			Game.getSnake().get(i).y = Game.getSnake().get(i - 1).y;
		}
	}
	
	private void collision() {
		// collision snake
		Rectangle head = new Rectangle((int) x, (int) y, Game.DIMENSION, Game.DIMENSION);
		for (int i = 1; i < Game.getSnake().size(); i++) {
			Rectangle body = new Rectangle((int) Game.getSnake().get(i).x, (int) Game.getSnake().get(i).y, Game.DIMENSION, Game.DIMENSION);

			if (head.equals(body)) {
				System.out.println("Game Over!");
				score = 0;
				speed = 2.5;
				Game.getSnake().clear();
				up = false;
				down = false;
				left = false;
				right = false;
				for (int j = 0; j < Game.DIMENSION; j++) {
					Game.getSnake().add(new Snake(0, 0,Game.DIMENSION,Game.DIMENSION));
				}
			}
		}
	}

	public void render(Graphics graphic) {
		graphic.setFont(new Font("Arial", Font.BOLD, 25));
		graphic.setColor(Color.white);
		scoreboard = "" + score;
		graphic.drawString("Score: " + scoreboard, 20, 30);
		
		graphic.setColor(Color.white);
		graphic.fillRect((int) x, (int) y, Game.DIMENSION, Game.DIMENSION);
	}

	// ------(Sets/Gets)----------------------

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}

}
