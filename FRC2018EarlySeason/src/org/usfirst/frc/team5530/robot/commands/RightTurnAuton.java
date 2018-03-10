package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;


public class RightTurnAuton extends CommandGroup {
	
	public RightTurnAuton() {
		addSequential(new InitializeMotors());
		addSequential(new TurnArc(52, Math.PI/2, Math.PI/5, .05, "right"));
		addSequential(new TurnOL("left"));
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(54));
//		addSequential(new TurnOL("left"));
//		Timer.delay(3);
//		addSequential(new TurnOL("right"));
	}

}
 