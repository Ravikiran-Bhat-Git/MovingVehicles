package com.moving.vehicle.bean;

import java.awt.Color;

/**
 * @author Ravikiran
 *
 *Bean class defining a basic vehicle.
 */
public class Vehicle {

	protected Position position;
	protected Velocity velocity;
	protected Color c;

	public Vehicle() {
		super();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

}