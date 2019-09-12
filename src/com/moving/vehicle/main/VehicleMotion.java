package com.moving.vehicle.main;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.moving.vehicle.bean.Panel;
import com.moving.vehicle.model.VehicleModel;

import javax.swing.JOptionPane;

/**
 * @author Ravikiran
 * 
 * The main class that creates the vehicles from the model and 
 * defines the Swing components in the run method.
 */
public class VehicleMotion implements Runnable {

	private boolean vehicleRunning;
	private VehicleModel[] vehiclesArray;
	private int vehicleCount;
	private VehiclePanel vehiclePanel;
	private JFrame frame;

	public boolean isVehicleRunning() {
		return vehicleRunning;
	}

	public void setVehicleRunning(boolean vehicleRunning) {
		this.vehicleRunning = vehicleRunning;
	}

	public int getVehicleCount() {
		return vehicleCount;
	}

	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}

	/**
	 * The constructor sets the count of the instantiated vehicles
	 * and creates our array of vehicles. 
	 */
	public VehicleMotion() {
		int count = instantiateVehicles();
		setVehicleCount(count);
		vehiclesArray = new VehicleModel[getVehicleCount()];

		for (int i = 0; i < vehiclesArray.length; i++) {
			vehiclesArray[i] = new VehicleModel();
		}
	}

	/**
	 * Method to allow user to instantiate the vehicles.
	 * Allows for a minimum of 1 and maximum of 10 vehicles
	 * to be instantiated.
	 * @return
	 */
	private int instantiateVehicles() {
		int count = 3;
		boolean requireInput =true;
		while (requireInput) {
			try {
				String enteredMsg = JOptionPane.showInputDialog(
						"Enter the number of vehicles to instantiate (Max upto 10)");
				if(enteredMsg!=null) {
					count = Integer.parseInt(enteredMsg);
					if(0 == count) {
						JOptionPane.showMessageDialog(null, 
								"Invalid input!! A minimum of 1 vehicle must be instantiated.");
						continue;
					}
					else if(count > 10) {
						JOptionPane.showMessageDialog(null, 
								"Invalid input!!Only a maximum of 10 vehicles can be instantiated.");
						continue;
					}
				}
				else {
					System.exit(0);
				}
				requireInput = false;
			} catch (NumberFormatException | HeadlessException e) {
				JOptionPane.showMessageDialog(null, 
						"Invalid input!! Enter a valid value between 1 and 10.");
			}
		}
		return count;
	}

	public void moveVehicle() {
		for (int i = 0; i <vehicleCount; i++) {
			vehiclesArray[i].move();
		}
	}

	/**
	 * The frame properties are set here and a windowlistener
	 * is used to stop the thread once the application ends.
	 * A loop is used to constantly check the status of the application
	 * and repaints our graphics as long as it is running.
	 */
	@Override
	public void run()
	{
		frame = new JFrame();   
		this.vehicleRunning = true;
		vehiclePanel = new VehiclePanel(vehiclesArray);
		frame.pack();
		frame.add(vehiclePanel);
		frame.setVisible(true);
		frame.setSize(Panel.WIDTH, Panel.HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setTitle("Moving Vehicles");

		while(vehicleRunning)
		{
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent event) {
					exitApplication();
				}
			});
			moveVehicle();
			vehiclePanel.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}

	private void exitApplication() {
		setVehicleRunning(false);
		frame.dispose();
		System.exit(0);
	}

	public static void main(String[] args) {
		VehicleMotion vehicleMotion = new VehicleMotion();
		Thread d = new Thread(vehicleMotion);
		d.start();
	}

}
