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
import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardAuto extends Command {
	private Timer shotTimer =  new Timer(); 

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveForwardAuto() {
    	requires(Robot.driveSystem);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shotTimer.start();
    	Robot.driveSystem.setAutoShift(false);
    	Robot.driveSystem.setWantsLowGear(true);
    	Robot.driveSystem.setWantsHighGear(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		
    	
    	Robot.driveSystem.drive(2, 2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return shotTimer.hasPeriodPassed(1);
       
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.drive(0, 0);
    	Robot.driveSystem.setAutoShift(true);
    	Robot.driveSystem.setWantsLowGear(false);
    	Robot.driveSystem.setWantsHighGear(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSystem.drive(0, 0);
    	Robot.driveSystem.setAutoShift(true);
    	Robot.driveSystem.setWantsLowGear(false);
    	Robot.driveSystem.setWantsHighGear(false);
    }
}