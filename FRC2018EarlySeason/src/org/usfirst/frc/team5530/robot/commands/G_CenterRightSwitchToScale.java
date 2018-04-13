package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterRightSwitchToScale extends CommandGroup{
	
	public G_CenterRightSwitchToScale() {
		addSequential(new DriveOneForward(60, "l"));
		addParallel(new ElevatorMid());
		addSequential(new DriveForward(90));
		addSequential(new Pause(.5));
		addSequential(new Deliver());
		addSequential(new DriveForward(-50));
		addSequential(new ElevatorBot());
	}
	
}