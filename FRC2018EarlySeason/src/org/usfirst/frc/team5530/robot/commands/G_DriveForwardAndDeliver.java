package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_DriveForwardAndDeliver extends CommandGroup {
	
	public G_DriveForwardAndDeliver() {
		addSequential(new InitializeMotors());
		addSequential(new ArmModular("Mid"));
		addParallel(new DriveForward(100));
		addSequential(new ArmModular("Top"));
		addSequential(new Deliver());		
	}

}
