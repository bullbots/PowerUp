package org.usfirst.frc1891.PowerUp.commands;

import org.usfirst.frc1891.PowerUp.Robot;
import org.usfirst.frc1891.PowerUp.commands.AutoModes.Side;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class CheckSideScale extends ConditionalCommand {
	
	Side targetSide;

	public CheckSideScale(Side side, Command onTrue, Command onFalse) {
		super(onTrue, onFalse);
		targetSide = side;
	}
	
	public CheckSideScale(Side side, Command onTrue) {
		super(onTrue);
		targetSide = side;
	}

	@Override
	protected boolean condition() {
		if (targetSide == Side.left) {
			if (Robot.message.charAt(1) == 'L') {
				return true;
			}
		}
		else if (targetSide == Side.right) {
			if (Robot.message.charAt(1) == 'R') {
				return true;
			}
		}
		return false;
	}

}
