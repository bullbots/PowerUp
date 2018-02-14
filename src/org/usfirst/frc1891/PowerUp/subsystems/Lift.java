package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.LiftOperatorControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private final DigitalInput intakeBottom = RobotMap.intakeBottom;
	private final DigitalInput stage2Bottom = RobotMap.stage2Bottom;
	private boolean winchTimerStarted = false;
	private boolean timeWinchStarted = false;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new LiftOperatorControl());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void periodic() {
    	
    	
	    if (resetWinch) {
	    	liftMotor.set(0);
	    	setWinchRatchetEngaged(false);
	    	winch.set(-0.5);
	    	
	    	if (!winchTimerStarted) {
	    		winchTimer.start();
	    		winchTimerStarted  = true;
	    	}
	    	if (winchTimer.hasPeriodPassed(1)) {
		   		winch.stopMotor();
		   		winchTimer.stop();
				winchTimer.reset();
		   		winchTimerStarted = false;
		   	}
	    }
	    else if (runWinch) {
	    	liftMotor.set(0);
	    	setWinchRatchetEngaged(true);
	    	setLiftRatchetEngaged(false);
	    	
	    	if (!stage2Bottom.get()) {
		    	winch.set(0.7);
	    	}
	    	else {
	    		winch.set(0);
	    	}
//	    	
//	    	if (!timeWinchStarted) {
//	    		timeWinch.start();
//	    		timeWinchStarted = true;
//	    	}
//	    	timeWinch.start();
//	    	
//	    	if (timeWinch.hasPeriodPassed(.5)) {
//		   		winch.stopMotor();
//		   		timeWinch.stop();
//		   		timeWinch.reset();
//	    		timeWinchStarted = false;
//		   	}		
	    }
    	else {
	    	// Main Lift Control
	    	if (closedLoopEnabled) {
	    		// TODO add closed loop junk
	    		liftMotor.set(0);
	    	}
	    	else {
	    		// Move lift up
	    		if(directionLift == 1) {
	    			liftMotor.set(ControlMode.PercentOutput, -0.5);
	    			setLiftRatchetEngaged(true);
	    		}
	    		// Move lift down
	    		else if(directionLift == -1) {
	    			liftMotor.set(ControlMode.PercentOutput, 0.2);
	    			setLiftRatchetEngaged(false);
	    		}
	    		// Hold lift position
	    		else {
	    			liftMotor.set(ControlMode.PercentOutput, 0);
	    			setLiftRatchetEngaged(true);
	    		}
	    	}
    	}
    	SmartDashboard.getBoolean("limitSwitch", intakeBottom.get());
    	SmartDashboard.getBoolean("limitSwitch", stage2Bottom.get());
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
    		winchRatchet.setAngle(0);
    	}
    	else {
    		//Disengage winchRatchet
    		winchRatchet.setAngle(50);
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
    	runWinch = false;
    }
    public void runWinch() {
    	runWinch = true;
    	resetWinch = false;
    }
    
    public void stopWinch() {
    	runWinch = false;
    	resetWinch = false;
    }
}

