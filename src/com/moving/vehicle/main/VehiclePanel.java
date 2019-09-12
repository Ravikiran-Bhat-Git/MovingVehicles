package com.moving.vehicle.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import com.moving.vehicle.bean.Velocity;
import com.moving.vehicle.interfaces.MotionDirectionControlInterface;
import com.moving.vehicle.model.VehicleModel;

/**
 * @author Ravikiran
 * 
 * This class is used to override JPanel's paintComponent method to draw our panel components. 
 * In addition, KeyListener interface is implemented to implement keypressed events for
 * direction control of vehicles. Methods from MotionDirectionControlInterface are 
 * overriden to change velocity along x or y axis in response to arrow key press event
 * or stop the vehicle on pressing 'S'.
 */
public class VehiclePanel extends JPanel implements KeyListener, MotionDirectionControlInterface{
	
	private static final long serialVersionUID = 1L;
	private VehicleModel[] vehiclesArray;
	private int vehicleNumber = 0;
	
	public VehiclePanel(VehicleModel[] vehiclesArray) {
		this.vehiclesArray = vehiclesArray;
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
	}

	/**
	 * The paintComponent method loops through the array and allows each 
	 * vehicle to draw itself. In addition a goal platform and a string 
	 * identifier is drawn for each vehicle.
	 */
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for(int i = 0; i<vehiclesArray.length; i++)
        {
        	vehiclesArray[i].draw(g2D,i);
        	
        	int goalX = (int) vehiclesArray[i].getPosition().getGoalX();
        	int goalY = (int) vehiclesArray[i].getPosition().getGoalY();
        	g2D.setColor(Color.BLACK);
        	Rectangle goalPlatform = new Rectangle(goalX,goalY,
        			VehicleModel.VEHICLE_BODY_WIDTH,VehicleModel.PLATFORM_BODY_WIDTH);
        	g2D.draw(goalPlatform);
        	g2D.fill(goalPlatform);
        	
        	g.drawString(String.valueOf(i), goalX+VehicleModel.VEHICLE_BODY_WIDTH, 
        			goalY-VehicleModel.GOAL_LABEL_DISPLACEMENT_Y);
        }
    }
	
	public void moveUp(VehicleModel vehicle) {
		double velY = vehicle.getVelocity().getVelY();
		double velX = 0;
		velY = -0.25;
		vehicle.setVelocity(new Velocity(velX, velY));
	}
	
	@Override
	public void moveDown(VehicleModel vehicle) {
		double velY = vehicle.getVelocity().getVelY();
		double velX = 0;
		velY = 0.25;
		vehicle.setVelocity(new Velocity(velX, velY));
	}
	
	@Override
	public void moveLeft(VehicleModel vehicle) {
		double velX = vehicle.getVelocity().getVelX();
		double velY = 0;
		velX = -0.25;
		vehicle.setVelocity(new Velocity(velX, velY));
	}
	
	@Override
	public void moveRight(VehicleModel vehicle) {
		double velX = vehicle.getVelocity().getVelX();
		double velY = 0;
		velX = 0.25;
		vehicle.setVelocity(new Velocity(velX, velY));
	}
	
	public void stopVehicle(VehicleModel vehicle) {
		double velX = 0;
		double velY = 0;
		vehicle.setVelocity(new Velocity(velX, velY));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		char keyChar = e.getKeyChar();
		
		if(code == KeyEvent.VK_UP) {
			moveUp(vehiclesArray[vehicleNumber]);
		}
		else if(code == KeyEvent.VK_DOWN) {
			moveDown(vehiclesArray[vehicleNumber]);
		}
		else if(code == KeyEvent.VK_LEFT) {
			moveLeft(vehiclesArray[vehicleNumber]);
		}
		else if(code == KeyEvent.VK_RIGHT) {
			moveRight(vehiclesArray[vehicleNumber]);
		}
		else if(code == KeyEvent.VK_S) {
			stopVehicle(vehiclesArray[vehicleNumber]);
		}
		else if(Character.getNumericValue(keyChar) % 1 == 0) {
			vehicleNumber = Character.getNumericValue(keyChar);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
