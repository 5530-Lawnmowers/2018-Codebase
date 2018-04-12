package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_StraightScaleAuton extends CommandGroup {

	public G_StraightScaleAuton(String side) { 
		addSequential(new DriveForward(235));
//		addSequential(new Pause(1));
		addSequential(new SimpleTurn(side, -Math.PI/2, 2));
		addSequential(new InitializeMotors());
		addSequential(new G_LiftTop());
		addSequential(new DriveForward(30));
		addSequential(new Deliver());
		addSequential(new DriveForward(-30));
		addSequential(new InitializeMotors());
		addSequential(new G_LiftBot());
	}
}