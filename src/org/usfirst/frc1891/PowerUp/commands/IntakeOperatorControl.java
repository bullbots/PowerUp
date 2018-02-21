package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOperatorControl extends Command {

    public IntakeOperatorControl() {
    	requires(Robot.intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!LiftOperatorControl.climbing) {
	    	if (Robot.oi.getCloseIntake()) {
				Robot.intake.close();
			}
			else if (Robot.oi.getOpenIntake()) {
				Robot.intake.open();
				Robot.intake.KickerIn();
			}
			if (Robot.oi.getKickBox()) {
				Robot.intake.KickerOut();
			}
			else {
				Robot.intake.KickerIn();
			}
    	}
    	else {
    		Robot.intake.open();
    		Robot.intake.KickerIn();
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
