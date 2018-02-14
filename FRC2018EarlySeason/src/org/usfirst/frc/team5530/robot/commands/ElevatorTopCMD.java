package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5530.robot.subsystems.Elevator;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class ElevatorTopCMD extends Command{
	public ElevatorTopCMD() {
		requires(Robot.elevator);
	}
	
	protected void initialize() {
		Elevator.setFollowing();
		Elevator.Elevator0.set(ControlMode.PercentOutput, 0);
		Timer.delay(0.5);
	}

	protected void execute() {
		Elevator.Elevator0.set(ControlMode.PercentOutput, .625); //There is a small pause after button press
		 
	}
	protected boolean isFinished() {
		if (!Elevator.elevatorSwitchTop.get()) return true;
		return false; 
	}
	protected void end() {
		Elevator.Elevator0.set(0);
	}
	protected void interrupted() {
		Elevator.Elevator0.set(0);
	}
	
	
	
}

