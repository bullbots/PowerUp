package org.usfirst.frc1891.PowerUp.commands.AutoModes;

import org.usfirst.frc1891.PowerUp.commands.CheckSideSwitch;
import org.usfirst.frc1891.PowerUp.commands.DriveForward;
import org.usfirst.frc1891.PowerUp.commands.ForwardPlaceCubeSwitch;
import org.usfirst.frc1891.PowerUp.commands.MiddleSwitch;
import org.usfirst.frc1891.PowerUp.commands.Turn;
import org.usfirst.frc1891.PowerUp.commands.getSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoSidesSwitch extends CommandGroup {

    public TwoSidesSwitch() {
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
    	addSequential(new getSide());
    	addSequential(new DriveForward(3));
    	addSequential(new CheckSideSwitch(Side.left, new MiddleSwitch(Side.left), new MiddleSwitch(Side.right)));
    }
}
