package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_TestTurn extends CommandGroup{
	public G_TestTurn() {
		addSequential(new SimpleTurn("l", Math.PI/6));
	}
}
