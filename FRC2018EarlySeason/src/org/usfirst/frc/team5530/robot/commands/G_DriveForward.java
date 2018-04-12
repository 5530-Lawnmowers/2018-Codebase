package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_DriveForward extends CommandGroup {
	
	public G_DriveForward(double length) {
		addSequential(new InitializeMotors());
		if(SmartDashboard.getBoolean("Manual Auton Control?", false)) addSequential(new DriveForward(SmartDashboard.getNumber("Input 1", 100)));
		else addSequential(new DriveForward(length));
	}

}
