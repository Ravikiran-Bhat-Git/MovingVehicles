package com.moving.vehicle.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.moving.vehicle.bean.Panel;
import com.moving.vehicle.bean.Position;
import com.moving.vehicle.bean.Vehicle;
import com.moving.vehicle.bean.Velocity;
import com.moving.vehicle.interfaces.VehicleModelInterface;


/**
 * @author Ravikiran
 * 
 * Class setting the vehicle body parameters and containing the methods to
 * let the vehicle draw itself and defining the vehicle motion.
 *
 */
public class VehicleModel extends Vehicle implements VehicleModelInterface {

	public static final int VEHICLE_BODY_WIDTH = 50;
	public static final int VEHICLE_BODY_HEIGHT = 17;
	public static final int DISPLACEMENT_X = 17;
	public static final int DISPLACEMENT_Y = 15;
	public static final int LABEL_DISPLACEMENT_Y = 20;
	public static final int VEHICLE_ROOF_SIZE = 17;
	public static final int GOAL_LABEL_DISPLACEMENT_Y = 2;
	public static final int PLATFORM_BODY_WIDTH = 2;
	
	public VehicleModel() {
		this.position = new Position();
		this.velocity = new Velocity();
		this.c = generateColor();
	}
	
	@Override 
	public void draw(Graphics2D g,int vehicleNumber)
    {
		int posX = (int)this.position.getX();
		int posY = (int)this.position.getY();
        g.setColor(c);
        Rectangle body = new Rectangle(posX,posY,
        								VEHICLE_BODY_WIDTH,
        								VEHICLE_BODY_HEIGHT);
        Rectangle roof = new Rectangle(posX+DISPLACEMENT_X,posY-DISPLACEMENT_Y,
        								VEHICLE_ROOF_SIZE,VEHICLE_ROOF_SIZE);
        g.draw(body);
        g.draw(roof);
        g.fill(body);
        g.fill(roof);
        g.drawString(String.valueOf(vehicleNumber), posX+DISPLACEMENT_X, 
        							posY-LABEL_DISPLACEMENT_Y);
    }
	
	/**
	 * The move method sets the velocity along x and y-axis.
	 * xUpper and yUpper are limits defined to prevent the 
	 * vehicle from moving off-screen. Exceeding these limits
	 * results in the vehicle velocity along the corresponding 
	 * axis to be inverted to make the vehicle move in the 
	 * opposite direction.
	 */
	@Override 
	public void move() {
		int lowerlimit = 0;
		int xUpper = Panel.WIDTH - VEHICLE_BODY_WIDTH;
		int yUpper = Panel.HEIGHT - (2*VEHICLE_BODY_HEIGHT);
				
		double x = this.position.getX();
		double y = this.position.getY();
		double velX = this.velocity.getVelX();
		double velY = this.velocity.getVelY();
		
		if(x < lowerlimit) {
			x = lowerlimit;
			velX = changeXDirectionMovement(x, velX);
		}
		else if(x > xUpper) {
			x = xUpper;
			velX = changeXDirectionMovement(x, velX);
		}
		if(y < lowerlimit) {
			y = lowerlimit;
			velY = changeYDirectionMovement(y, velY);
		}
		else if(y > yUpper) {
			y = yUpper;
			velY = changeYDirectionMovement(y, velY);
		}
		x += velX;
		y += velY;
		
		this.position.setX(x);
		this.position.setY(y);
	}

	private double changeYDirectionMovement(double y, double velY) {
		velY = -velY;
		this.position.setY(y);
		this.velocity.setVelY(velY);
		return velY;
	}

	private double changeXDirectionMovement(double x, double velX) {
		velX = -velX;
		this.position.setX(x);
		this.velocity.setVelX(velX);
		return velX;
	}

	@Override 
	public Color generateColor() {
        int R = (int) (Math.random() * 256);
        int G = (int) (Math.random() * 256);
        int B = (int) (Math.random() * 256);
        Color c = new Color(R, G, B);
        return c;
    }
	
}
