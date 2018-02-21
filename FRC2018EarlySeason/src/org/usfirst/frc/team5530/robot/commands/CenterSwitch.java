package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.DriverStation;

public class CenterSwitch extends CommandGroup {
	
	public CenterSwitch() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData.charAt(0) == 'l') {
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
		}else if(gameData.charAt(0) ==  'r'){
			addSequential(new InitializeMotors());
			addSequential(new DriveForward(52));
//			addSequential(new Turn(90));
			addSequential(new DriveForward(57));
//			addSequential(new Turn(-90));
			addSequential(new ArmMid());
			addParallel(new ArmTop());
			addSequential(new DriveForward(48));
			addSequential(new Deliver());
		}
	}
}
