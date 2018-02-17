package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class DriveForwardAndTurn extends CommandGroup {
	
	public DriveForwardAndTurn() {
		addSequential(new InitializeMotors());
		addSequential(new Turn());
	}

}
