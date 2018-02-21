package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class OpenIntake extends InstantCommand {

    public OpenIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }
    
    protected void initialize() {
    	Robot.intake.open();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.open();
    }

    protected void interrupted() {
    }
    
    protected void end() {
    }
}
