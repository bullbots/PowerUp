package org.usfirst.frc1891.PowerUp.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lights extends Subsystem {
	
	I2C arduino;
	int address;
	byte[] data = new byte[3];
	int value1;
	int value2;
	int value3;
	boolean batteryLow;
	
	/**
	 * @param address
	 */
	public Lights(int address) {
		arduino = new I2C(I2C.Port.kOnboard, address);
		this.address = address;
		value1 = 0;
		value2 = 0;
		value3 = 0;
		batteryLow = false;
		
		SmartDashboard.putNumber("Lights", 0);
		SmartDashboard.putNumber("Comma", 0);
		
	}
	
	public void periodic() {
		double comm = SmartDashboard.getNumber("Comma", 0);
		double Test = SmartDashboard.getNumber("Lights", 0);
		writeState((int)comm);
		writeDynamic((int) Test);
	}
	
	/**
	 * sets all bytes to a value, for use at start of game
	 * @param val1 defines the state for the arduino to enter
	 * @param val2 defines the dynamic level for the arduino to display
	 * @param val3 defines the team color
	 */
	public void write(int val1, int val2, int val3) {
		if (batteryLow) {
			data[0] = 7;
			
		}
		else {
			data[0] = (byte) val1;
		}
		data[1] = (byte) val2;
		data[2] = (byte) val3;
		arduino.writeBulk(data);
	}
	
	/**
	 * changes the current state of the arduino
	 * @param value defines the state for the arduino to enter
	 */
	public void writeState(int value) {
		value1 = (byte) value;
		write(value1,value2,value3);
	}
	
	/**
	 * changes dynamic value of the robot's current task
	 * @param value defines the dynamic level for the arduino to display
	 * 
	 */
	public void writeDynamic(int value) {
		value2 = (byte) value;
		write(value1,value2,value3);
		
	}
	
	/**
	 * changes team color value
	 * @param value val3 defines the team color
	 * 
	 */
	public void writeTeam(int value) {
		value3 = (byte) value;
		write(value1,value2,value3);
	}
	
	public void lowBattery() {
		batteryLow = true;
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

