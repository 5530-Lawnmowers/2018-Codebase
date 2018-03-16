package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_StartingPosition extends CommandGroup{
	
	public G_StartingPosition() {
		addParallel(new ElevatorMid());
		addParallel(new ArmModular("StartingPosition"));
	}
		
}
