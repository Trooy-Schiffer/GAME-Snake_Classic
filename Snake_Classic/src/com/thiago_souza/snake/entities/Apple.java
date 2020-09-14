package com.thiago_souza.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.thiago_souza.snake.main.Game;

public class Apple extends Entity {

	public Apple(double x, double y, int width, int height) {
		super(x, y, width, height);
	}

	public void render(Graphics graphic) {
		graphic.setColor(Color.red);
		graphic.fillRect((int) x, (int) y, Game.DIMENSION, Game.DIMENSION);
	}
}
