package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_LiftBot extends CommandGroup {
	
	public G_LiftBot() {
		addParallel(new ElevatorBot());
		addSequential(new ArmBot());
	}
}
