package com.thiago_souza.snake.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import com.thiago_souza.snake.entities.Apple;
import com.thiago_souza.snake.entities.Snake;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static boolean itsRunning;
	private static Thread thread;
	private static JFrame frame;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int DIMENSION = 15;
	private static Controls controls;

	private static BufferedImage image;
	private static List<Snake> snake;
	private static Apple apple;

	public Game() {
		controls = new Controls();
		addKeyListener(controls);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		startFrame();

		// Starting Objects
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		snake = new ArrayList<Snake>();
		for (int i = 0; i < DIMENSION; i++) {
			snake.add(new Snake(0, 0, DIMENSION, DIMENSION));
		}

		apple = new Apple(0, 0, DIMENSION, DIMENSION);
	}

	public void startFrame() {
		frame = new JFrame("Snake");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		itsRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		itsRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println("erro stop game.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void update() {
		snake.get(0).update();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics graphic = image.getGraphics();
		graphic.setColor(new Color(20, 20, 20));
		graphic.fillRect(0, 0, WIDTH, HEIGHT);

		// render game
		for (int i = 0; i < snake.size(); i++) {
			Snake s = snake.get(i);
			s.render(graphic);
		}

		apple.render(graphic);
		// -----

		graphic.dispose();
		graphic = bs.getDrawGraphics();
		graphic.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		bs.show();
	}

	@Override
	public void run() {
		// FPS
		long lastTime = System.nanoTime();
		double FramesPerSecond = 60.0;
		final double ns = 1000000000 / FramesPerSecond;
		double delta = 0;
		int frameRates = 0;
		double timer = System.currentTimeMillis();

		// Start Game
		while (itsRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				frameRates++;
				delta--;
			}

			// print FPS
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frameRates);
				frameRates = 0;
				timer += 1000;
			}
		}
		stop();
	}

	// ------(Sets/Gets)----------------------

	public static List<Snake> getSnake() {
		return snake;
	}

	public static Apple getApple() {
		return apple;
	}
}