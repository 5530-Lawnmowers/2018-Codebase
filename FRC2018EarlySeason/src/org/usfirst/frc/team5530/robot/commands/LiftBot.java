package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftBot extends CommandGroup {
	
	public LiftBot() {
		addParallel(new ElevatorBot());
		addSequential(new ArmBot());
	}
}
