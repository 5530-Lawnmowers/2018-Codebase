package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class TurnAuton extends CommandGroup {
	
	public TurnAuton() {
		addSequential(new InitializeMotors());
		addSequential(new ArcTurn(52, Math.PI/2, Math.PI/5, .05, "left"));
//		addSequential(new ArcTurn(10, Math.PI/2, Math.PI/4, .05, "right"));
//		addSequential(new DriveForward(38));
	}

}
