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

import org.usfirst.frc1891.PowerUp.subsystems.DriveSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static WPI_TalonSRX driveSystemLeftMasterTalon;
    public static WPI_TalonSRX driveSystemLeftSlaveTalon;
    public static WPI_TalonSRX driveSystemRightMasterTalon;
    public static WPI_TalonSRX driveSystemRightSlaveTalon;
	public static AHRS ahrs;
	public static final int timeoutMs = 10;
	
	public static final int ShifterLowPort = 1;
	public static final int ShifterHighPort = 0;

    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveSystemLeftMasterTalon = new WPI_TalonSRX(1);
        driveSystemLeftMasterTalon.set(ControlMode.PercentOutput, 0);
        driveSystemLeftMasterTalon.setInverted(false);
        driveSystemLeftMasterTalon.setSensorPhase(true);
        driveSystemLeftMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, timeoutMs);
        
        driveSystemLeftMasterTalon.configNominalOutputForward(0, timeoutMs);
        driveSystemLeftMasterTalon.configNominalOutputReverse(0, timeoutMs);
        driveSystemLeftMasterTalon.configPeakOutputForward(1, timeoutMs);
        driveSystemLeftMasterTalon.configPeakOutputReverse(-1, timeoutMs);
        
//        driveSystemLeftMasterTalon.configVoltageCompSaturation(12.5, timeoutMs);
//        driveSystemLeftMasterTalon.enableVoltageCompensation(true);
        
        driveSystemLeftMasterTalon.config_kF(0, 0.08497, timeoutMs); // calculated 0.08497, raised to 
        driveSystemLeftMasterTalon.config_kP(0, 0, timeoutMs); // 0.005
        driveSystemLeftMasterTalon.config_kI(0, 0, timeoutMs);
        driveSystemLeftMasterTalon.config_kD(0, 0, timeoutMs);
        
        driveSystemLeftMasterTalon.config_kF(1, 0.0413, timeoutMs);
        driveSystemLeftMasterTalon.config_kP(1, 0, timeoutMs);
        driveSystemLeftMasterTalon.config_kI(1, 0, timeoutMs);
        driveSystemLeftMasterTalon.config_kD(1, 0, timeoutMs);
        
        driveSystemLeftMasterTalon.configMotionCruiseVelocity(DriveSystem.feetPerSecToEncoderUnits(DriveSystem.enforcedLowGearTopSpeedFeet), timeoutMs); 
        driveSystemLeftMasterTalon.configMotionAcceleration(DriveSystem.feetPerSecToEncoderUnits(DriveSystem.maxLowGearAcceleration), timeoutMs);
        
        
        
        driveSystemLeftSlaveTalon = new WPI_TalonSRX(2);
        driveSystemLeftSlaveTalon.set(ControlMode.Follower, driveSystemLeftMasterTalon.getDeviceID());
        driveSystemLeftSlaveTalon.setInverted(false);
        
        
        driveSystemRightMasterTalon = new WPI_TalonSRX(3);
        driveSystemRightMasterTalon.set(ControlMode.PercentOutput, 0);
        driveSystemRightMasterTalon.setInverted(true); // Inverted because right side at normal polarity goes backwards
        driveSystemRightMasterTalon.setSensorPhase(true);
        driveSystemRightMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, timeoutMs);

        driveSystemRightMasterTalon.configNominalOutputForward(0, timeoutMs);
        driveSystemRightMasterTalon.configNominalOutputReverse(0, timeoutMs);
        driveSystemRightMasterTalon.configPeakOutputForward(1, timeoutMs);
        driveSystemRightMasterTalon.configPeakOutputReverse(-1, timeoutMs);
        
//        driveSystemRightMasterTalon.configVoltageCompSaturation(12, timeoutMs);
//        driveSystemRightMasterTalon.enableVoltageCompensation(true);
        
        driveSystemRightMasterTalon.config_kF(0, 0.09, timeoutMs); // calculated was 0.08757 raised to 0.09 for tuning
        driveSystemRightMasterTalon.config_kP(0, 0, timeoutMs); // 0.004
        driveSystemRightMasterTalon.config_kI(0, 0, timeoutMs);
        driveSystemRightMasterTalon.config_kD(0, 0, timeoutMs);
        
        driveSystemRightMasterTalon.config_kF(1, 0.045, timeoutMs);
        driveSystemRightMasterTalon.config_kP(1, 0, timeoutMs);
        driveSystemRightMasterTalon.config_kI(1, 0, timeoutMs);
        driveSystemRightMasterTalon.config_kD(1, 0, timeoutMs);
		
        driveSystemRightMasterTalon.configMotionCruiseVelocity(DriveSystem.feetPerSecToEncoderUnits(DriveSystem.enforcedLowGearTopSpeed), timeoutMs); 
        driveSystemRightMasterTalon.configMotionAcceleration(DriveSystem.feetPerSecToEncoderUnits(DriveSystem.maxLowGearAcceleration), timeoutMs);
        
        driveSystemRightSlaveTalon = new WPI_TalonSRX(4);
        driveSystemRightSlaveTalon.set(ControlMode.Follower, driveSystemRightMasterTalon.getDeviceID());
        driveSystemRightSlaveTalon.setInverted(true); // Inverted because right side at normal polarity goes backwards
        

//        ahrs = new AHRS(Port.kMXP);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
