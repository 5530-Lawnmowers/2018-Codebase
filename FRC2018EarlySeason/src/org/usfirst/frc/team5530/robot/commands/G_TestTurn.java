package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class G_TestTurn extends CommandGroup{
	public G_TestTurn() {
		addSequential(new InitializeMotors());
		addSequential(new SimpleTurn(SmartDashboard.getString("Str Input 1", "l"), Math.PI * SmartDashboard.getNumber("Input 1", 0.5), 2));
	}
}
