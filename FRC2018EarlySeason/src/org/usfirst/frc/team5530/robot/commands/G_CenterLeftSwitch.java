package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.DriverStation;

public class G_CenterLeftSwitch extends CommandGroup {
	
	public G_CenterLeftSwitch() {
			addSequential(new InitializeMotors());
			addSequential(new DriveForward(52));
			addSequential(new InitializeMotors());
//			addSequential(new Turn(-90));
			addSequential(new InitializeMotors());
			addSequential(new DriveForward(62));
			addSequential(new InitializeMotors());
//			addSequential(new Turn(90));
			addSequential(new InitializeMotors());
			addParallel(new ElevatorMid());
			addSequential(new DriveForward(48));
			addSequential(new Deliver());
	}
}
