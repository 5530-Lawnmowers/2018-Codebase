package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class TestMotors extends Command{
	
	public TestMotors() {
		super("TestMotors");
		requires(Robot.lift);
		requires(Robot.intake);
		requires(Robot.drivetrain);
		requires(Robot.climb);
	}
	
	
	protected void initialize() {
		Lift.setFollowing();
		Intake.setFollowing();
		Drivetrain.setFollowing();
		Climb.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		
				
	}
	protected boolean isFinished() {
		
		return true; // maybe true?
	}
	protected void end() {
		
	}
	protected void interrupted() {
		Lift.Arm.set(ControlMode.PercentOutput, 0);
		
	}
	
	
	
}
