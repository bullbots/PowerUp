package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.IntakeOperatorControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Intake extends Subsystem {
	
	private final DoubleSolenoid gripperSolenoid;
	private final WPI_TalonSRX wheeledIntakeLeft = RobotMap.wheeledIntakeLeft;
	private final WPI_TalonSRX wheeledIntakeRight = RobotMap.wheeledIntakeRight;
	public Value wantedGripperState;
	public Value currentGripperState;

	
	public Intake() {
    	wheeledIntakeLeft.set(0);
    	wheeledIntakeRight.set(0);
    	gripperSolenoid = new DoubleSolenoid(RobotMap.intakeSolenoidOpenPort, RobotMap.intakeSolenoidClosePort);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeOperatorControl());
    }
    
    public void periodic() {
	    if (wantedGripperState != currentGripperState) {
	    	gripperSolenoid.set(Value.kForward);
	    	currentGripperState = Value.kForward;
	    }
	    else if (gripperSolenoid.get() != Value.kOff) {
	    	gripperSolenoid.set(Value.kOff);
	    }
	    
    }
	
    public void set(double value) {
    	wheeledIntakeLeft.set(value);
    	wheeledIntakeRight.set(value);
    }
	
	public void close() {
		wantedGripperState = Value.kReverse;
	}
	
	public void open() {
		wantedGripperState = Value.kForward;
	}
	
	
	
	}