package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftOperatorControl extends Command {

    public LiftOperatorControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getLiftOperatorOverride()) {
    		Robot.lift.setClosedLoopControl(false);
    		int output = 0;
//    		System.out.println(Robot.oi.getLiftControl());
    		if (-Robot.oi.getLiftControl() > 0.2) output = 1;
    		else if (-Robot.oi.getLiftControl() < -0.2) output = -1;
    		Robot.lift.setLiftDirection(output);
    	}
    	else {
    		Robot.lift.setClosedLoopControl(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
