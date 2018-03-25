package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_SideSwitch extends CommandGroup{
		
	public G_SideSwitch(String side) {
		addSequential(new DriveForward(128));
		addSequential(new ArmModular("top"));
		addSequential(new SimpleTurn(side, Math.PI/2));
		addSequential(new InitializeMotors()); //TODO Add this to every Auton Command Group
		addSequential(new DriveForwardTimed(12, 2));
		addSequential(new Deliver());
	}
}
