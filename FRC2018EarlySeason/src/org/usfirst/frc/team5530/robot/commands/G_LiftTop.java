package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_LiftTop extends CommandGroup {
	
	public G_LiftTop() {
		addParallel(new ElevatorTop());
		addParallel(new ArmModular("Top"));
	}
}
