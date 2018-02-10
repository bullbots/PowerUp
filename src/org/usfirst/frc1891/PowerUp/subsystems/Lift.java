package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.LiftOperatorControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	private final WPI_TalonSRX liftMotor = RobotMap.liftMotorTalon;
	private final Spark winch = RobotMap.winch;
	
	private final Servo liftRachet = RobotMap.liftRachetServo;
	
	private boolean closedLoopEnabled = false;
	
	private int directionLift = 0;
	
	private boolean resetWinch = false;
	
	private boolean runWinch = false;
	
	private final Servo winchRatchet = RobotMap.winchRatchetServo;
	
	Timer winchTimer = new Timer();
	
	Timer timeWinch = new Timer();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new LiftOperatorControl());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void periodic() {
    	
    	// Winch control
    	if (winchTimer.hasPeriodPassed(1)) {
    		winch.stopMotor();
    		winchTimer.stop();
    		winchTimer.reset();
    	}
    	if (resetWinch) {
    		setWinchRatchetEngaged(false);
    		winch.set(-0.5);
    		winchTimer.start();
    		resetWinch = false;
    	}
    	if (timeWinch.hasPeriodPassed(.5)) {
    		winch.stopMotor();
    		timeWinch.stop();
    		timeWinch.reset();
    	}		
    	if (runWinch) {
    		setWinchRatchetEngaged(true);
    		winch.set(0.5);
    		timeWinch.start();
    		runWinch = false;
    	}
    	
    	
    	// Main Lift Control
    	if (closedLoopEnabled) {
    		// TODO add closed loop junk
    		liftMotor.set(0);
    	}
    	else {
    		// Move lift up
    		if(directionLift == 1) {
    			liftMotor.set(ControlMode.PercentOutput, -0.3);
    			setLiftRatchetEngaged(true);
    		}
    		// Move lift down
    		else if(directionLift == -1) {
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			setLiftRatchetEngaged(false);
    		}
    		// Hold lift position
    		else {
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			setLiftRatchetEngaged(true);
    		}
    	}
    }
    
    private void setLiftRatchetEngaged(boolean value) {
    	if (value) {
    		//Engage ratchet
    		liftRachet.setAngle(90);
    	}
    	else {
    		//Disengage ratchet
    		liftRachet.setAngle(50);
    	}
    }
    
    private void setWinchRatchetEngaged(boolean value) {
		if (value) {
    		//Engage winchRatchet
    		winchRatchet.setAngle(20);
    	}
    	else {
    		//Disengage winchRatchet
    		winchRatchet.setAngle(0);
    	}
    }
    
    public void setClosedLoopControl(boolean closedLoopEnabledValue) {
    	closedLoopEnabled = closedLoopEnabledValue;
    }
    
    public void setLiftDirection(int direction) {
    	 directionLift = direction;
    }
    public void resetWinch() {
    	resetWinch = true;
    }
    public void runWinch() {
    	runWinch = true;
    }
}

