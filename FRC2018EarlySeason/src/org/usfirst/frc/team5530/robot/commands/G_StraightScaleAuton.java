package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_StraightScaleAuton extends CommandGroup {

	public G_StraightScaleAuton(String side) { 
		addSequential(new DriveForward(250));
//		addSequential(new Pause(1));
		addSequential(new SimpleTurn(side, -2*Math.PI/5, 2));
		addSequential(new InitializeMotors());
		addSequential(new G_LiftTop());
		addSequential(new DriveForwardTimed(20, 1.5));
		addSequential(new Deliver());
		addSequential(new DriveForwardTimed(-20, 1.5));
		addSequential(new InitializeMotors());
		addSequential(new G_LiftBot());
	}
}