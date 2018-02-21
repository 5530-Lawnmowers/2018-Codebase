package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command sets the motors to 0 after auton

public class InitializeMotors extends Command{
	public InitializeMotors() {
		super("InitializeMotorsCMD");
		requires(Robot.intakeSS);
		requires(Robot.climbSS);
		requires(Robot.elevatorSS);
		requires(Robot.drivetrainSS);
	}
	
	
	protected void initialize() {
		IntakeSS.setFollowing();
		ClimbSS.setFollowing();
		ElevatorSS.setFollowing();
		DrivetrainSS.setFollowing();
	}

	protected void execute() {
	
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		IntakeSS.Intake0.set(ControlMode.PercentOutput, 0); 
		ClimbSS.Climb0.set(ControlMode.PercentOutput, 0);
		ElevatorSS.Elevator0.set(ControlMode.PercentOutput, 0);
		//Elevator.Elevator0.setSelectedSensorPosition(x, 0, 0); We still need to find this value
		DrivetrainSS.frontRight.set(ControlMode.PercentOutput, 0);
		DrivetrainSS.frontLeft.set(ControlMode.PercentOutput, 0);
		DrivetrainSS.frontRight.setSelectedSensorPosition(0, 0, 0);
		DrivetrainSS.frontLeft.setSelectedSensorPosition(0, 0, 0);
		ClimbSS.servo0.set(.66);
		ClimbSS.servo1.set(.66);
		Timer.delay(.1);
	}
	protected void interrupted() {
		
	}
	
	
	
}

