package com.moving.vehicle.bean;

/**
 * @author Ravikiran
 *
 *Bean class defining random start and end coordinates.
 */
public class Position {
	private double x;
	private double y;
	private double goalX;
	private double goalY;
	
	public Position() {
		this.x = Math.random() * Panel.WIDTH;
		this.y = Math.random() * Panel.HEIGHT;
		this.goalX = Math.random() * (Panel.WIDTH-200);
		this.goalY = Math.random() * (Panel.HEIGHT-200);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getGoalX() {
		return goalX;
	}
	public void setGoalX(double goalX) {
		this.goalX = goalX;
	}
	public double getGoalY() {
		return goalY;
	}
	public void setGoalY(double goalY) {
		this.goalY = goalY;
	}
	

}
