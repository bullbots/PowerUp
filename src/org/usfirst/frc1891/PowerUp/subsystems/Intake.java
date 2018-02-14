package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private final DoubleSolenoid solenoid;
	
	public Intake() {
		solenoid = new DoubleSolenoid(RobotMap.intakeSolenoidOpenPort, RobotMap.intakeSolenoidClosePort);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void open() {
		solenoid.set(Value.kForward);
	}
	
	public void close() {
		solenoid.set(Value.kReverse);
	}
}

