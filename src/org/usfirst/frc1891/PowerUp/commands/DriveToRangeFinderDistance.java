package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.DriveTrainControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToRangeFinderDistance extends PIDCommand {

    private boolean hasRun = false;
    private double motorOutput = 0;
    private double target = 0;

	public DriveToRangeFinderDistance(double targetInchesFromWall) {
		// takes about 4 feet to slow down, giving 6 feet for safety.
		// 72 * kP = 3
		// kP = 0.04
		
    	super(-0.16, 0, 0);
    	this.getPIDController().setInputRange(20, 90);
    	this.getPIDController().setOutputRange(0, 2);
    	getPIDController().setAbsoluteTolerance(1);
    	
    	target = targetInchesFromWall;
    	setSetpoint(target);
    	
    	getPIDController().enable();
    	
    	requires(Robot.driveSystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!hasRun) {
    		
	    	Robot.driveSystem.setControlMode(DriveTrainControlMode.OperatorControl);
	    	
	    	hasRun = true;
    	}
    	SmartDashboard.putNumber("motorOutput", motorOutput);
    	Robot.driveSystem.drive(motorOutput, motorOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	hasRun  = false;
    	getPIDController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	hasRun = false;
    	getPIDController().disable();
    }

	@Override
	protected double returnPIDInput() {
		return Robot.driveSystem.getLeftRange();
	}

	@Override
	protected void usePIDOutput(double output) {
		motorOutput = -output;
	}
}
