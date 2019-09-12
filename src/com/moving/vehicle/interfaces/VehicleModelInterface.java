package com.moving.vehicle.interfaces;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * @author Ravikiran
 *
 */
public interface VehicleModelInterface {
	
	public void draw(Graphics2D g,int vehicleNumber);
	
	public Color generateColor();
	
	public void move();

}
