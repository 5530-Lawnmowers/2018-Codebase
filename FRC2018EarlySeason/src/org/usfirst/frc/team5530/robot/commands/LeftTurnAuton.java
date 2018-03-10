package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class LeftTurnAuton extends CommandGroup {
	
	public LeftTurnAuton() {
		addSequential(new InitializeMotors());
		addSequential(new TurnArc(52, Math.PI/2, Math.PI/5, .05, "left"));
		addSequential(new TurnOL("right"));
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(49));
	}

}
 