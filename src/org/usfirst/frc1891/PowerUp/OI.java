// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1891.PowerUp;

import org.usfirst.frc1891.PowerUp.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc1891.PowerUp.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton joystickButton1;
    public Joystick throttleStick;
    public Joystick turningStick;
    public Joystick operatorStick;
    public XboxController controller;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

//        turningStick = new Joystick(1);
        
//        throttleStick = new Joystick(0);
        operatorStick = new Joystick(2);
        
        controller = new XboxController(0);
        
        joystickButton1 = new JoystickButton(controller, 3);
        joystickButton1.whenPressed(new UpDownAngle());
        

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("RunMotors", new RunMotors(3));
        SmartDashboard.putData("Drive Forward", new DriveForward(10));

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getThrottleStick() {
        return throttleStick;
    }

    public Joystick getTurningStick() {
        return turningStick;
    }
    
    public double getThrottle() {
//    	return throttleStick.getRawAxis(1); // Two Sticks
    	return cube(controller.getRawAxis(1)); // X-Box
//    	return throttleStick.getRawAxis(1); // Brennen's mess
    }
    
    public double getTurning() {
//    	return turningStick.getRawAxis(4); // Two Sticks
    	return square(-controller.getRawAxis(4));	// X-Box
//    	if (Math.abs(throttleStick.getRawAxis(2)) > 0.2) return -throttleStick.getRawAxis(2); // Brennen's mess
//    	else return -throttleStick.getRawAxis(0);
    }
    
    public boolean getQuickTurn() {
//    	return turningStick.getRawButton(2); // Two Sticks
//    	return throttleStick.getRawButton(8); //Brock's Gamecube
    	return controller.getRawButton(6); // X-Box
//    	if (Math.abs(throttleStick.getRawAxis(2)) > 0.2) return true; // Brennen's mess
//    	else return false;
    }
    
    public boolean getLowGear() {
    	return !controller.getRawButton(9); // X-Box
//    	return throttleStick.getRawButton(2); // Two Sticks
//    	return throttleStick.getRawButton(6); // Brennen's mess
    }
    
    public boolean getHighGear() {
    	return controller.getRawButton(9); // X-Box
//    	return turningStick.getRawButton(3); // Two Sticks
//    	return throttleStick.getRawButton(5); // Brennen's mess
    }
    
    public boolean getCloseIntake() {
    	if (controller.getRawAxis(3) >= 0.6) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean getOpenIntake() {
    	if (controller.getRawAxis(2) >= 0.6) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean getKickBox() {
		return controller.getAButton();
	}
    
    public void setRumble(double value) {
    	controller.setRumble(RumbleType.kLeftRumble, value);
    	controller.setRumble(RumbleType.kRightRumble, value);
    }
    
    public double getLiftControl() {
    	return operatorStick.getRawAxis(1);
    }
    
    public boolean getLiftOperatorOverride() {
    	return operatorStick.getRawButton(1);
    }
    
    public boolean getStartClimb() {
    	return operatorStick.getRawButton(9);
    }
    
    public boolean getResetClimb() {
//    	return operatorStick.getRawButton(button);
    	return false;
    }
    
    public boolean getStopClimb() {
    	return operatorStick.getRawButton(10);
    }
    
    private double square(double value) {
		return (value * value) * (value / Math.abs(value));
	}
    
    private double cube(double value) {
		return (value * value * value);
	}

	


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

