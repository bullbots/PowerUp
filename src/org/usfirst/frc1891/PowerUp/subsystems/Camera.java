package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.UpDownAngle;
import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem.Gear;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	Servo cameraServo;
	
    public boolean isCameraUp = false;

	public Camera() {
		CameraServer.getInstance().startAutomaticCapture();
		
		cameraServo = new Servo(RobotMap.CameraServoPort1);
		DownCamera();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void UpCamera() {
    	cameraServo.setPosition(0);	
    	isCameraUp = true;
	}
    
    public void DownCamera() {
    	cameraServo.setPosition(100);
    	isCameraUp = false;
    }
    
}