package org.usfirst.frc1891.PowerUp.commands.AutoModes;

import org.usfirst.frc1891.PowerUp.commands.CheckSideSwitch;
import org.usfirst.frc1891.PowerUp.commands.CloseIntake;
import org.usfirst.frc1891.PowerUp.commands.DriveForward;
import org.usfirst.frc1891.PowerUp.commands.ForwardPlaceCubeSwitch;
import org.usfirst.frc1891.PowerUp.commands.getSide;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class OneSideSwitch extends CommandGroup {

    public OneSideSwitch(Side side) {
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
    	addSequential(new CloseIntake());
//    	addSequential(new DriveForward(8));
//    	addSequential(new TimedCommand(0.5));
    	addSequential(new CheckSideSwitch(side, new ForwardPlaceCubeSwitch(11, 20), new CrossLine()));
    }
}
