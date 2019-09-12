package com.moving.vehicle.main;

import javax.swing.SwingUtilities;

import com.moving.vehicle.model.VehicleModel;

/**
 * @author Ravikiran
 *Class to controlling the loop for vehicle animation 
 */
public class ObjectAnimationManager implements Runnable{

	private VehicleModel[] vehiclesArray;
	private VehicleMotion vehicleMotion;
	private boolean applicationRunning;
	
	public ObjectAnimationManager(VehicleMotion vehicleMotion, VehicleModel[] vehiclesArray) {
		this.vehiclesArray = vehiclesArray;
		this.vehicleMotion = vehicleMotion;
		this.applicationRunning = true;
	}
	
	@Override
	public void run() {
		while(applicationRunning) {
			moveObjects();
			repaintObjects();
			sleep();
		}		
	}

	private void moveObjects() {
		for (int i = 0; i < vehiclesArray.length; i++) {
			vehiclesArray[i].move();
		}
		
	}

	private void repaintObjects() {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	vehicleMotion.repaintMovingVehicle();
            }
        });
	}

	private void sleep() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void setApplicationRunning(boolean applicationRunning) {
		this.applicationRunning = applicationRunning;
	}

}
