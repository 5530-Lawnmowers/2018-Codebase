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
//This command sets the motors to 0 after auton

public class InitializeMotorsCMD extends Command{
	public InitializeMotorsCMD() {
		super("InitializeMotorsCMD");
		requires(Robot.intake);
		requires(Robot.climb);
		requires(Robot.lift);
		requires(Robot.drivetrain);
	}
	
	
	protected void initialize() {
		Intake.setFollowing();
		Climb.setFollowing();
		Lift.setFollowing();
		Drivetrain.setFollowing();
	}

	protected void execute() {
	
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		Intake.Intake0.set(ControlMode.PercentOutput, 0); 
		Climb.Climb0.set(ControlMode.PercentOutput, 0);
		Lift.Lift1.set(ControlMode.PercentOutput, 0);
		Drivetrain.frontRight.set(ControlMode.PercentOutput, 0);
		Drivetrain.frontLeft.set(ControlMode.PercentOutput, 0);
		Drivetrain.frontRight.setSelectedSensorPosition(0, 0, 0);
		Drivetrain.frontLeft.setSelectedSensorPosition(0, 0, 0);
	}
	protected void interrupted() {
		
	}
	
	
	
}

