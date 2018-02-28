package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.commands.AutoModes.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardTurnScale extends CommandGroup {

    public ForwardTurnScale(Side side) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveForwardAuto(-1, 0.25));
    	addSequential(new DriveForwardAuto(-2, 0.25));
    	addSequential(new DriveForwardAuto(-3, 0.5 / 3.0));
    	addSequential(new DriveForwardAuto(-4, 1.0 / 4.0));
    	addSequential(new DriveForwardAuto(-5, 19.0 / 5.0));
    	addSequential(new DriveForwardAuto(-4, 1.0 / 4.0));
    	addSequential(new DriveForwardAuto(-3, 0.5 / 3.0));
    	addSequential(new DriveForwardAuto(-2, 0.25 / 2.0));
    	addSequential(new DriveForwardAuto(-1, 0.25 / 1.0));
//    	addSequential(new DriveForward(25));
    	addSequential(new CheckSideScale(side, new Turn(-90), new Turn(90)));
    	addSequential(new setLiftPosition(66));
    	addSequential(new DriveForwardAuto(1, 2.0 / 1.0));
    	addSequential(new DriveForwardAuto(-1, 3.0 / 1.0));
//    	addSequential(new DriveForward(1.33));
//    	addSequential(new DriveForwardAuto(1, 1));
    	addSequential(new OpenIntake());
    	addSequential(new DriveForwardAuto(1, 3.0 / 1.0));
    	addSequential(new setLiftPosition(0));
    	
    }
}
