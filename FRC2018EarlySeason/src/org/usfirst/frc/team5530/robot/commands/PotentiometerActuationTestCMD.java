package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogInput;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PotentiometerActuationTestCMD extends Command{
	
	WPI_TalonSRX Controller;
	double time;
	double counter = 0;
	boolean finished = false;
	boolean isGoingUp = true;
	AnalogInput currentPotentiometer;
	double top = 450;
	double bot = 2600;
	
	
	public PotentiometerActuationTestCMD(WPI_TalonSRX Controller, AnalogInput currentPotentiometer, double time) {
		this.currentPotentiometer = currentPotentiometer;
		this.time = time*50;
		requires(Robot.arm);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		if (currentPotentiometer.getValue() >= top && isGoingUp && counter <= time * .5) Controller.set(-0.75);
		else if (isGoingUp) {
			Controller.set(0.75);
			isGoingUp = false;
		}
		else if (currentPotentiometer.getValue() <= bot && counter < time) Controller.set(0.75);
		else finished = true;
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
		Controller.set(0);
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

