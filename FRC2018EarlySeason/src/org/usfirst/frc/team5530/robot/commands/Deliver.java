package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.IntakeSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

//This command runs the motors in the intake system to dispense the cube from the robot

public class Deliver extends Command{
	int counter;
	public Deliver() {
		requires(Robot.intakeSS);
	}
	
	
	protected void initialize() {
		counter = 0;
		IntakeSS.setFollowing();
	}

	protected void execute() {
		IntakeSS.Intake0.set(ControlMode.PercentOutput, -0.6);
		counter++;
	}
	protected boolean isFinished() {
		if (counter <= 50) return false;
		return true; 
	}
	protected void end() {
		IntakeSS.Intake0.set(0);
	}
	protected void interrupted() {
		IntakeSS.Intake0.set(0);
	}
	
	
	
}

