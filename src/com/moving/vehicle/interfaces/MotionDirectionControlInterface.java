package com.moving.vehicle.interfaces;

import com.moving.vehicle.model.VehicleModel;

/**
 * @author Ravikiran
 *
 */
public interface MotionDirectionControlInterface {
	
	public void moveUp(VehicleModel vehicle);
	
	public void moveDown(VehicleModel vehicle);
	
	public void moveLeft(VehicleModel vehicle);
	
	public void moveRight(VehicleModel vehicle);
	
	public void stopVehicle(VehicleModel vehicle);

}
