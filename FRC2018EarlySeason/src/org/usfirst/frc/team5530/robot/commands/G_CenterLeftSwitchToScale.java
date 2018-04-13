package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterLeftSwitchToScale extends CommandGroup{
	
	public G_CenterLeftSwitchToScale() {
		addParallel(new ElevatorMid());
		addSequential(new DriveForward(115));
		addSequential(new Pause(.5));
		addSequential(new Deliver());
		addSequential(new DriveForward(-110));
		addSequential(new ElevatorBot());
		addSequential(new DriveOneForward(40,"l"));
		addSequential(new DriveForward(50));
	}
	
}
