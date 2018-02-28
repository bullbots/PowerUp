package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class getSide extends Command {


	@Override
	protected boolean isFinished() {
		Robot.message = DriverStation.getInstance().getGameSpecificMessage();
		return Robot.message.length() != 0;
	}

}
