package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.commands.AutoModes.Side;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitch extends CommandGroup {

    public MiddleSwitch(Side side) {
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

    	double turn = 0;
    	if (side == Side.left) {
    		turn = -45;
    	}
    	else if (side == Side.right) {
    		turn = 45;
    	}
    	addSequential(new Turn(turn));
    	addParallel(new setLiftPosition(24));
    	addSequential(new DriveForward(6));
    	addSequential(new Turn(-turn));
    	addSequential(new ForwardPlaceCubeSwitch(3, 3));
    	addSequential(new OpenIntake());
    }
}
