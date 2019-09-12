package com.moving.vehicle.bean;

/**
 * @author Ravikiran
 *
 *Bean class defining random start velocity along each coordinate.
 */
public class Velocity {
	public static final double START_VELOCITY = 0.2;
	private double velX;
	private double velY;
	
	public Velocity() {
		this.velX = Math.random() * START_VELOCITY;
		this.velY = Math.random() * START_VELOCITY;
	}
	public Velocity(double velX, double velY) {
		this.velX = velX;
		this.velY = velY;
	}
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	

}
