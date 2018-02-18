package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForwardAndDeliver extends CommandGroup {
	
	public DriveForwardAndDeliver() {
		addSequential(new InitializeMotors());
		addSequential(new ArmMid());
		addParallel(new ArmTop());
		addSequential(new DriveForward(100));
		addSequential(new Deliver());		
	}

}
