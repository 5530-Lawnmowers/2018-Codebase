package org.usfirst.frc.team5530.robot.commands;

import org.usfirst.frc.team5530.robot.triggers.Profile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterLeftSwitch extends CommandGroup{
	
	public G_CenterLeftSwitch() {
		addParallel(new ElevatorMid());
		addSequential(new MotionProfile(Profile.leftSwitchProfile2_5));
		addSequential(new Pause(1));
		addSequential(new Deliver());
		addParallel(new ElevatorBot());
		addSequential(new DriveForward(-110));
	}
	
}
