package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftTop extends CommandGroup {
	
	public LiftTop() {
		addParallel(new ElevatorTop());
		addSequential(new ArmTop());
	}
}
