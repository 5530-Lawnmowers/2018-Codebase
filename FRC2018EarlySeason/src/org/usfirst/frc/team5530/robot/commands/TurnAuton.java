package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class TurnAuton extends CommandGroup {
	
	public TurnAuton() {
		addSequential(new InitializeMotors());
		addSequential(new ArcTurn(52, Math.PI/2, Math.PI/5, .05, "left"));
		addSequential(new TurnOL());
		addSequential(new DriveForward(38));
	}

}
