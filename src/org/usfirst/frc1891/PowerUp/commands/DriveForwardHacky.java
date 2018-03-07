// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1891.PowerUp.commands;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1891.PowerUp.Robot;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.DriveTrainControlMode;

/**
 *
 */
public class DriveForwardHacky extends Command {

	
	//TODO weird stutter, maybe PID gains?
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	private int target;
	private Timer startup;
	private Timer override;
	private double expectedTime;
	private boolean hasRun = false;
	private double position;
	private boolean finished = false;
	private int loopCounter = 0;
	
	private int currentLoop = 0;
	private int totalLoops;
	private int endAccelerationLoop;
	private int beginDeccelerationLoop;
	private double leftSpeed;
	private double rightSpeed;
	private double acceleration = 110;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveForwardHacky(double inputTarget) {
    	requires(Robot.driveSystem);
    	target = Robot.driveSystem.feetToEncoderUnits(inputTarget);
    	if (target > 22000) {
    		int tempTarget = target - 22000;
    		totalLoops = tempTarget / 220;
    		totalLoops += 200;
    		endAccelerationLoop = 100;
    		beginDeccelerationLoop = totalLoops - endAccelerationLoop;
    	}
    	else {
    		totalLoops = (int) (Math.sqrt(target) / 55);
    		endAccelerationLoop = totalLoops / 2;
    		beginDeccelerationLoop = totalLoops / 2;
    	}
		System.out.println("beginDeccelerationLoop: " + beginDeccelerationLoop);
		System.out.println("endAccelerationLoop: " + endAccelerationLoop);
		System.out.println("totalLoops: " + totalLoops);
    	
//    	startup = new Timer();
//    	override = new Timer();
    	
//    	expectedTime = inputTarget < 5 ? 6 : (2 + (inputTarget * 1.6) + 2);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (!hasRun) {
//    		startup.start();
//    		override.start();
    		
//	    	Robot.driveSystem.setControlMode(DriveTrainControlMode.DriveForward);
    		Robot.driveSystem.setControlMode(DriveTrainControlMode.OperatorControl);
	    	
	    	
//	    	System.out.println("Target: " + target);
//	    	System.out.println("Position Left: " + Robot.driveSystem.getLeftPosition());
//	    	System.out.println("Position Right: " + Robot.driveSystem.getRightPosition());
//	    	
//	    	
////	    	Robot.driveSystem.setMotionMagicTarget(target);
//	    	
//	    	
//	    	System.out.println("Position Left: " + Robot.driveSystem.getLeftPosition());
//	    	System.out.println("Position Right: " + Robot.driveSystem.getRightPosition());
	    	
	    	
//	    	Robot.driveSystem.startMotion();
	    	hasRun = true;
	    	
//	    	position = Robot.driveSystem.getLeftPosition();
    	}
    	
    	if (currentLoop <= endAccelerationLoop) {
    		leftSpeed += acceleration;
    		rightSpeed += acceleration;
    		System.out.println("accelerating");
    	}
    	else if (currentLoop > beginDeccelerationLoop) {
    		leftSpeed -= acceleration;
    		rightSpeed -= acceleration;
    		System.out.println("deccelerating");
    	}
    	currentLoop++;
    	
    	Robot.driveSystem.drive(leftSpeed, rightSpeed);
//    	if (loopCounter == 10) {
//    		if (position == Robot.driveSystem.getLeftPosition()) {
//    			finished = true;
//    		}
//    		else {
//    			position = Robot.driveSystem.getLeftPosition();
//    		}
//    		loopCounter = 0;
//    	}
//    	else {
//    		loopCounter++;
//    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return currentLoop == totalLoops;
//    	return startup.hasPeriodPassed(0.5) && Math.abs(position) > Math.abs(target * (8.0 / 10.0)) && finished; //TODO add position confirmation
//    	return (startup.hasPeriodPassed(0.5) && Robot.driveSystem.hasReachedMotionTarget());
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
//    	System.out.println("Motion finished");
////    	System.out.println(Robot.driveSystem.motionMagicSetPoint);
//    	System.out.println("Has hit target: " + Robot.driveSystem.hasReachedMotionTarget());
//    	System.out.println(finished);
//    	System.out.println("Error Left: " + Robot.driveSystem.getLeftError());
//    	System.out.println("Error Right: " + Robot.driveSystem.getRightError());
    	System.out.println("Position Left: " + Robot.driveSystem.getLeftPosition());
    	System.out.println("Position Right: " + Robot.driveSystem.getRightPosition());
    	System.out.println("Target Left: " + Robot.driveSystem.getLeftTarget());
    	System.out.println("Target Right: " + Robot.driveSystem.getRightTarget());
    	
//    	startup.stop();
//    	startup.reset();
//    	override.stop();
//    	override.reset();
//    	
//    	Robot.driveSystem.stopMotion();
    	Robot.driveSystem.drive(0, 0);
    	
    	hasRun = false;
//    	finished = false;
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("Motion Interupted");
    	end();
    }
}