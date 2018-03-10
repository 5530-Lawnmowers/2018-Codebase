package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;

public class StartingPosition extends CommandGroup{
	
	public StartingPosition() { 
		addSequential(new ElevatorMid());
		addParallel(new moveArm("StartingPosition"));
	}
}
