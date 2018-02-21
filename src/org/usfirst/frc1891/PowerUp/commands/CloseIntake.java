package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CloseIntake extends InstantCommand {

    public CloseIntake() {
//    	requires(Robot.intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.close();
    }
    
    protected void interrupted() {
    }
    
    protected void end() {
    }
}
