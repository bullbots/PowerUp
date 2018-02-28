package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.Robot;
import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.LiftOperatorControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
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
	private int closedLoopTarget = 0;
	
	private int directionLift = 0;
	
	private boolean resetWinch = false;
	
	private boolean runWinch = false;
	
	private final Servo winchRatchet = RobotMap.winchRatchetServo;
	
	Timer winchTimer = new Timer();
	
	Timer timeWinch = new Timer();
	
	private final DigitalInput intakeBottom = RobotMap.intakeBottom;
	private final DigitalInput stage2Bottom = RobotMap.stage2Bottom;
	private final DigitalInput liftTop = RobotMap.liftTop;
	
	private boolean winchTimerStarted = false;
	private boolean timeWinchStarted = false;
	private boolean liftDownwardStarting;
	private boolean downwardTimerStarted;
	private Timer downwardTimer = new Timer();
	
	private DoubleSolenoid buddyBar = new DoubleSolenoid(RobotMap.buddyBarSolenoidOutPort, RobotMap.buddyBarSolenoidInPort);
	private boolean notTriggered;
	
	public Lift() {
		buddyBar.set(Value.kReverse);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new LiftOperatorControl());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void periodic() {
    	
    	if (!intakeBottom.get() && notTriggered) {
    		liftMotor.setSelectedSensorPosition(0, 0, 5);
    		notTriggered = false;
    	}
    	else if (intakeBottom.get()) {
    		notTriggered = true;
    	}
    	
    	//this checks if the reset winch is called 
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
	    //This is for climbing
	    else if (runWinch) {
	    	//if the winch is ingaged
	    	liftMotor.set(0);
	    	setWinchRatchetEngaged(true);
	    	//this is the climber
	    	setLiftRatchetEngaged(false);
	    	//this is the lifter
	    	
	    	if (!stage2Bottom.get()) {
		    	winch.set(1);
	    	}
	    	else {
	    		winch.set(0);
	    	}
	    	
	    	if (Robot.oi.getBuddyBarButton()) {
	    		Value state = buddyBar.get();
	    		if (state == Value.kReverse) {
		    		buddyBar.set(Value.kForward);	
	    		}
	    		else {
	    			buddyBar.set(Value.kReverse);
	    		}
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
	    		
	    		//0.01 inches per encoder tick
	    		//100 ticks per inch
	    		
	    		if (-liftMotor.getSelectedSensorPosition(0) < closedLoopTarget - 100 && !atTop()) {
	    			liftMotor.set(ControlMode.PercentOutput, -0.9);
	    			setLiftRatchetEngaged(true);
	    			downwardTimerStarted = false;
	    		}
	    		else if (-liftMotor.getSelectedSensorPosition(0) > closedLoopTarget + 100 && !intakeAtBottom()) {
	    			setLiftRatchetEngaged(false);
	    			liftMotor.set(0.6);
//	    			if (!downwardTimerStarted) {
//	    				downwardTimer.start();
//	    				downwardTimerStarted = true;
//	    				liftDownwardStarting = true;
//	    			}
//	    			if (liftDownwardStarting) {
//	    				liftMotor.set(ControlMode.PercentOutput, -0.5);
//	    				liftDownwardStarting = !downwardTimer.hasPeriodPassed(0.01);
//	    			}
//	    			else {
//	    				liftMotor.set(ControlMode.PercentOutput, 0);
//		    			setLiftRatchetEngaged(true);
//		    			downwardTimerStarted = false;
//	    			}
	    		}
	    		else {
	    			liftMotor.set(0);
	    		}
//	    		liftMotor.set(0);
	    	}
	    	else {
	    		// Move lift up
	    		if(directionLift == 1) {
	    			liftMotor.set(ControlMode.PercentOutput, -0.9);
	    			setLiftRatchetEngaged(true);
	    			downwardTimerStarted = false;
//    				downwardTimer.stop();
//    				downwardTimer.reset();
	    		}
	    		// Move lift down
	    		else if(directionLift == -1 && intakeBottom.get()) {
	    			setLiftRatchetEngaged(false);
//	    			if (!downwardTimerStarted) {
//	    				downwardTimer.start();
//	    				downwardTimerStarted = true;
//	    				liftDownwardStarting = true;
//	    			}
//	    			if (liftDownwardStarting) {
//	    				liftMotor.set(ControlMode.PercentOutput, -0.5);
//	    				liftDownwardStarting = !downwardTimer.hasPeriodPassed(0.01);
//	    			}
//	    			else {
	    				liftMotor.set(ControlMode.PercentOutput, 0.6);
//	    			}
	    		}
	    		// Hold lift position
	    		else {
	    			liftMotor.set(ControlMode.PercentOutput, 0);
	    			setLiftRatchetEngaged(true);
//	    			downwardTimerStarted = false;
//    				downwardTimer.stop();
//    				downwardTimer.reset();
	    		}
	    	}
    	}
    	SmartDashboard.getBoolean("limitSwitch", intakeBottom.get());
    	SmartDashboard.getBoolean("limitSwitch", stage2Bottom.get());
    	SmartDashboard.putNumber("Lift Position: ", liftMotor.getSelectedSensorPosition(0));
    }
    
    private void setLiftRatchetEngaged(boolean value) {
    	if (value) {
    		//Engage ratchet
    		liftRachet.setAngle(50);
    	}
    	else {
    		//Disengage ratchet
    		liftRachet.setAngle(90);
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
    
    public void setClosedLoopTarget(double targetInches) {
    	closedLoopTarget = (int) (targetInches * 100);
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
    
    public boolean intakeAtBottom() {
    	return !intakeBottom.get();
    }
    
    public boolean secondStageAtBottom() {
    	return stage2Bottom.get();
    }
    
    public boolean atTop() {
    	return !liftTop.get();
    }
}

