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
	boolean flag = false;
	DigitalInput maxSwitch;
	DigitalInput minSwitch;
	
	
	public ElevatorTestCMD(WPI_TalonSRX Controller, double time, DigitalInput maxSwitch, DigitalInput minSwitch) {
		this.Controller = Controller;
		this.time = time*50;
		this.maxSwitch = maxSwitch;
		this.minSwitch = minSwitch;
		requires(Robot.lift);
	}
	
	
	protected void initialize() {
		Lift.setFollowing();
	}

	protected void execute() {
		if (Lift.liftSwitch0.get() && Lift.liftSwitch1.get()) {
			if (counter <= time) Controller.set(1);
			else if (time < counter && counter <= time*1.1) Controller.set(0);
			else if (time*1.1 < counter && counter <= time*2) Controller.set(-1);
			else {
				Controller.set(0);
				flag = true;
			}
			counter++;
		}
		else
			flag = true;
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		Controller.set(0);
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

