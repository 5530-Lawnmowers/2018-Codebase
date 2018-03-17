package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class G_DriveForward extends CommandGroup {
	
	public G_DriveForward() {
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(100));	
	}

}
