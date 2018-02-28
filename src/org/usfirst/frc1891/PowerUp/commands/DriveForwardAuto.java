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
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.DriveTrainControlMode;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.Gear;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardAuto extends Command {
	private Timer shotTimer =  new Timer(); 
	private double time;
	private double power;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveForwardAuto(double power, double time) {
    	requires(Robot.driveSystem);
    	this.power = power;
    	this.time = time;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shotTimer.start();
    	Robot.driveSystem.setControlMode(DriveTrainControlMode.OperatorControl);
    	Robot.driveSystem.setAutoShift(false);
    	Robot.driveSystem.setWantedGear(Gear.LowGear);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		
    	
    	Robot.driveSystem.drive(power, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return shotTimer.hasPeriodPassed(time);
       
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSystem.drive(0, 0);
    	Robot.driveSystem.setAutoShift(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSystem.drive(0, 0);
    	Robot.driveSystem.setAutoShift(true);
    }
}
