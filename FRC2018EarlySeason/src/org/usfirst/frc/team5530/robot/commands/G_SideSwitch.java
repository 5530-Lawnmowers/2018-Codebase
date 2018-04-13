package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_SideSwitch extends CommandGroup{
		
	public G_SideSwitch(String side) {
		if(SmartDashboard.getBoolean("Manual Auton Control?", false)) addSequential(new DriveForward(SmartDashboard.getNumber("Input 1", 100)));
		else addSequential(new DriveForward(128));
		addSequential(new ArmModular("top"));
		if(SmartDashboard.getBoolean("Manual Auton Control?", false)) addSequential(new SimpleTurn(SmartDashboard.getString("Str Input 1", side), Math.PI*SmartDashboard.getNumber("Input 2", 0.5), 2));
		else addSequential(new SimpleTurn(side, 3*Math.PI/5, 2));
		addSequential(new InitializeMotors()); //TODO Add this to every Auton Command Group
		if(SmartDashboard.getBoolean("Manual Auton Control?", false)) addSequential(new DriveForwardTimed(SmartDashboard.getNumber("Input 3", 12), SmartDashboard.getNumber("Input 4", 2)));
		else addSequential(new DriveForwardTimed(12, 2));
		addSequential(new Deliver());
	}
}
