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
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1891.PowerUp.CheesyDriveInterpreter;
import org.usfirst.frc1891.PowerUp.Robot;
import org.usfirst.frc1891.PowerUp.TankDriveSignal;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.Gear;

/**
 *
 */
public class JoystickDrive extends Command {

	
	CheesyDriveInterpreter cheesy = new CheesyDriveInterpreter(0.15, 0.15); // normal 0.1, 0.05
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public JoystickDrive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveSystem);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (Robot.oi.getLowGear()) {
    		Robot.driveSystem.setWantsLowGear(true);
    		Robot.driveSystem.setWantsHighGear(false);
    	}
    	else if (Robot.oi.getHighGear()) {
    		Robot.driveSystem.setWantsLowGear(false);
    		Robot.driveSystem.setWantsHighGear(true);
    	}
    	
    	double throttleStick = Robot.oi.getThrottle();
    	double turningStick = Robot.oi.getTurning(); 
    	boolean quickTurn = Robot.oi.getQuickTurn();
    	
    	
    	TankDriveSignal signal = cheesy.calculateSignal(throttleStick, turningStick, quickTurn);
    	
    	double topSpeed;
    	if (Robot.driveSystem.currentGear == Gear.LowGear) {
    		topSpeed = DriveSystem.enforcedLowGearTopSpeedFeet;
    	}
    	else {
    		topSpeed = DriveSystem.enforcedHighGearTopSpeedFeet;
    	}
    	
    	double leftSpeed = signal.leftMotor * topSpeed;
    	double rightSpeed = signal.rightMotor * topSpeed;
    	
    	Robot.driveSystem.drive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
