package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterRightSwitch extends CommandGroup{
	
	public G_CenterRightSwitch(String side) {
		addSequential(new DriveForward(52));
		addSequential(new SimpleTurn("r", Math.PI/2));
		addSequential(new DriveForward(57));
		addSequential(new SimpleTurn("l", Math.PI/2));
		addSequential(new ArmModular("Top"));
		addSequential(new DriveForward(48));
		addSequential(new Deliver());
	}
	
}
