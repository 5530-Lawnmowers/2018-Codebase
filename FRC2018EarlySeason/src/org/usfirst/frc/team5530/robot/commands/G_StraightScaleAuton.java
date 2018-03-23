package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_StraightScaleAuton extends CommandGroup {

	public G_StraightScaleAuton() { //Just add a parameter to determine left vs right
		addSequential(new DriveForward(210));
		//TODO Add turn here
		addSequential(new ElevatorTop());
		addSequential(new DriveForward(10));
		addSequential(new Deliver());
		addSequential(new DriveForward(-10));
		addSequential(new ElevatorBot());
	}
}
