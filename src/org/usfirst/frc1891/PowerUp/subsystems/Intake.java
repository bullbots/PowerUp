package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private final DoubleSolenoid gripperSolenoid;
	private final DoubleSolenoid kickerSolenoid;
	
	public Intake() {
		gripperSolenoid = new DoubleSolenoid(RobotMap.intakeSolenoidOpenPort, RobotMap.intakeSolenoidClosePort);
		kickerSolenoid = new DoubleSolenoid(RobotMap.kickerSolenoidOutPort, RobotMap.kickerSolenoidInPort);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void open() {
		if (gripperSolenoid.get() != Value.kForward) {
			gripperSolenoid.set(Value.kForward);
		}
	}
	
	public void close() {
		if (gripperSolenoid.get() != Value.kReverse) {
			gripperSolenoid.set(Value.kReverse);
			kickerSolenoid.set(Value.kReverse);
		}
	}
	
	public void KickerOut() {
		if (gripperSolenoid.get() == Value.kReverse) {
			kickerSolenoid.set(Value.kReverse);
		}
		else if (kickerSolenoid.get() != Value.kForward) {
			kickerSolenoid.set(Value.kForward);
		}
	}
	
	public void KickerIn() {
		if (kickerSolenoid.get() != Value.kReverse) {
			kickerSolenoid.set(Value.kReverse);
		}
	}
}

