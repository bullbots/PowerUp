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
		gripperSolenoid.set(Value.kForward);
	}
	
	public void close() {
		gripperSolenoid.set(Value.kReverse);
	}
	
	public void KickerOut() {
		kickerSolenoid.set(Value.kForward);
	}
	
	public void KickerIn() {
		kickerSolenoid.set(Value.kReverse);
	}
}

