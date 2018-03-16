package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.DriverStation;

public class G_ArmTest extends CommandGroup{
	
	public static double count = 0;
	
	public G_ArmTest() {
		count = 0;
		for (int i = 0; i < 2000; i++) {
			addSequential(new ArmModular("Top"));
			addSequential(new ArmModular("Bot"));
		}
	}
}
