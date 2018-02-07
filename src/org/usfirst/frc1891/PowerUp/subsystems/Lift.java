package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	private final WPI_TalonSRX liftMotor = RobotMap.liftMotorTalon;
	
	private final Servo liftRachet = RobotMap.liftRachetServo;
	
	private boolean closedLoopEnabled = false;
	
	private int directionLift = 0;
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void periodic() {
    	if (closedLoopEnabled) {
    		// TODO add closed loop junk
    	}
    	else {
    		if(directionLift == 1) {
    			liftMotor.set(ControlMode.PercentOutput, 0.1);
    			liftRachet.setAngle(90);
    		}
    		else if(directionLift == -1) {
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			liftRachet.setAngle(120);
    		}
    		else {
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			liftRachet.setAngle(90);
    		}
    	}
    }
    
    public void setClosedLoopControl(boolean closedLoopEnabledValue) {
    	closedLoopEnabled = closedLoopEnabledValue;
    }
    
    public void setLiftDirection(int direction) {
    	 directionLift = direction;
    }
}

