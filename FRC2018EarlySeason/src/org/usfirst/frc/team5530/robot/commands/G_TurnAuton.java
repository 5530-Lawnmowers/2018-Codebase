package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class G_TurnAuton extends CommandGroup {
	
	public G_TurnAuton() {
		addSequential(new InitializeMotors());
		addSequential(new TurnMM(52, Math.PI/2, Math.PI/5, .05, "left"));
		addSequential(new TurnOL());
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(49));
	}

}
 