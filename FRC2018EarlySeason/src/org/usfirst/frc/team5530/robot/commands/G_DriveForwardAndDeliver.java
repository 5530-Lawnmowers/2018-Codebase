package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_DriveForwardAndDeliver extends CommandGroup {
	
	public G_DriveForwardAndDeliver() {
		addSequential(new InitializeMotors());
		addSequential(new ArmModular("Mid"));
		addParallel(new ArmModular("Top"));
		if(SmartDashboard.getBoolean("Manual Auton Control?", false)) addSequential(new DriveForward(SmartDashboard.getNumber("Input 1", 100)));
		else addSequential(new DriveForward(100));
		addSequential(new Deliver());		
	}

}
