package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorTestCMD extends Command{
	
	WPI_TalonSRX Controller;
	double time;
	double counter = 0;
	boolean finished = false;
	boolean limitHit = false;
	DigitalInput maxSwitch;
	DigitalInput minSwitch;
	
	
	public ElevatorTestCMD(WPI_TalonSRX Controller, double time, DigitalInput maxSwitch, DigitalInput minSwitch) {
		this.Controller = Controller;
		this.time = time*50;
		this.maxSwitch = maxSwitch;
		this.minSwitch = minSwitch;
		requires(Robot.elevator);
	}
	 
	
	protected void initialize() {
		Elevator.setFollowing();
	}

	protected void execute() {
		if (!Elevator.elevatorSwitchTop.get()) limitHit = true;
		if (limitHit) {
			Controller.set(-0.08);
			if (!Elevator.elevatorSwitchBot.get()) finished = true;
		}
		else if (counter <= time) Controller.set(.25);
		else if (time < counter && counter <= time*2) {
			Controller.set(-0.08);
			if (!Elevator.elevatorSwitchBot.get()) finished = true;
		}
		else finished = true;
		counter++;
		//OI.buttons[5].cancelWhenPressed(this);
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
		Controller.set(0);
		Robot.armMotor0Test.start();
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

