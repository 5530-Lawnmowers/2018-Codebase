package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_StraightScaleAuton extends CommandGroup {

	public G_StraightScaleAuton(String side) { 
		addSequential(new DriveForward(210));
		addSequential(new SimpleTurn(side, Math.PI/12));
//		addSequential(new G_LiftTop());
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(10));
		addSequential(new Deliver());
		addSequential(new InitializeMotors());
		addSequential(new DriveForward(-10));
		addSequential(new G_LiftBot());
	}
}
