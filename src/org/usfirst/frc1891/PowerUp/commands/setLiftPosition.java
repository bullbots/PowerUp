package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class setLiftPosition extends InstantCommand {

	private double target;
	
    public setLiftPosition(double target) {
    	this.target = target;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.setClosedLoopControl(true);
    	Robot.lift.setClosedLoopTarget(target);
    }
}
