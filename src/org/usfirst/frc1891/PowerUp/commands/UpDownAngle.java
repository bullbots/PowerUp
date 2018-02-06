package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class UpDownAngle extends InstantCommand {

    public UpDownAngle() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    	if (Robot.camera.isCameraUp) {
    		Robot.camera.DownCamera();
    	}
    	else {
    		Robot.camera.UpCamera();
    	}
    	
    }

}
