/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose: contain all info + help methods the buttons of Pause screen.*/

package ui;

import java.awt.Rectangle;

public class PauseButton {

	protected int x, y, width, height;
	protected Rectangle bounds;

	public PauseButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		createBounds();
	}

	private void createBounds() {
		bounds = new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
