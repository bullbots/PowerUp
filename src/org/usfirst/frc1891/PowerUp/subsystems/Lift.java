package org.usfirst.frc1891.PowerUp.subsystems;

import org.usfirst.frc1891.PowerUp.RobotMap;
import org.usfirst.frc1891.PowerUp.commands.LiftOperatorControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {

	private final WPI_TalonSRX liftMotor = RobotMap.liftMotorTalon;
	
	private final Servo liftRachet = RobotMap.liftRachetServo;
	
	private boolean closedLoopEnabled = false;
	
	private int directionLift = 0;
	
	private final DigitalInput intakeBottom = RobotMap.intakeBottom;
	private final DigitalInput stage2Bottom = RobotMap.stage2Bottom;
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new LiftOperatorControl());
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void periodic() {
    	if (closedLoopEnabled) {
    		// TODO add closed loop junk
    		liftMotor.set(0);
    	}
    	else {
//    		System.out.println("Lift running");
    		if(directionLift == 1) {
//    			System.out.println("up");
    			liftMotor.set(ControlMode.PercentOutput, 0.1);
    			liftRachet.setAngle(0);
    		}
    		else if(directionLift == -1) {
//    			System.out.println("down");
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			liftRachet.setAngle(180);
    		}
    		else {
//    			System.out.println(directionLift);
    			liftMotor.set(ControlMode.PercentOutput, 0);
    			liftRachet.setAngle(0);
    		}
    	}
    	SmartDashboard.getBoolean("limitSwitch", intakeBottom.get());
    	SmartDashboard.getBoolean("limitSwitch", stage2Bottom.get());
    }
    
    public void setClosedLoopControl(boolean closedLoopEnabledValue) {
    	closedLoopEnabled = closedLoopEnabledValue;
    }
    
    public void setLiftDirection(int direction) {
    	 directionLift = direction;
    }
}

