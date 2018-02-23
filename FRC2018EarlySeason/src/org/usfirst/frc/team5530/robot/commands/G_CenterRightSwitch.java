package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_CenterRightSwitch extends CommandGroup {
	
	public G_CenterRightSwitch() {
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(52));
//		addSequential(new Turn(90));
		addSequential(new DriveForward(57));
//		addSequential(new Turn(-90));
		addSequential(new ArmMid());
		addParallel(new ArmTop());
		addSequential(new DriveForward(48));
		addSequential(new Deliver());
	}
}
