package org.usfirst.frc.team5530.robot.commands;
import org.usfirst.frc.team5530.robot.triggers.Profile;;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterRightSwitch extends CommandGroup{
	
	public G_CenterRightSwitch() {
		addParallel(new ElevatorMid());
		addSequential(new MotionProfile(Profile.rightSwitchProfile2_5));
		addSequential(new Pause(1));
		addSequential(new Deliver());
		addParallel(new ElevatorBot());
		addSequential(new DriveForward(-110));
	}
	
}