package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	Robot.intake.set(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!LiftOperatorControl.climbing) {

			boolean gripperClosed;
    		
	    	if (Robot.oi.getCloseIntake()) {
				Robot.intake.close();
				gripperClosed = true;
			}
			else if (Robot.oi.getOpenIntake()) {
				Robot.intake.open();
				gripperClosed = false;	
			}
	    	if (gripperClosed = false) {
		    	if (Robot.oi.getIntakeTest()) {
		    		Robot.intake.set(-0.40);
		    	}
		    	else if (Robot.oi.getSpitOut()) {
		    		Robot.intake.set(.40);
		    		
		    	}
	    	}
	    	else if (gripperClosed = true) {
	    		Robot.intake.set(0);
	    		
	    	}
    	}
    	else {
    		Robot.intake.open();
    		Robot.intake.set(0);
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
