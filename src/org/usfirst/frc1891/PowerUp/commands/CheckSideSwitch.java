package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.commands.AutoModes.Side;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class CheckSideSwitch extends ConditionalCommand {
	
	Side targetSide;

	public CheckSideSwitch(Side side, Command onTrue, Command onFalse) {
		super(onTrue, onFalse);
		targetSide = side;
	}
	
	public CheckSideSwitch(Side side, Command onTrue) {
		super(onTrue);
		targetSide = side;
	}

	@Override
	protected boolean condition() {
		String message = DriverStation.getInstance().getGameSpecificMessage();
		if (targetSide == Side.left) {
			if (message.charAt(0) == 'L') {
				return true;
			}
		}
		else if (targetSide == Side.right) {
			if (message.charAt(0) == 'R') {
				return true;
			}
		}
		return false;
	}

}
