package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//Disclaimer - do not read both comments, unless you are confused
//In this command, when the joystick trigger is held, the joystick will move the arm on top of the lift (Robert's lame version)
//This command allows the driver to hold the joystick trigger while controlling the joystick to move the arm on top of the lift (Lawrence's version)

public class Pause extends Command{
	
	double time;
	double count;
	
	public Pause(double time) {
		this.time = 50 * time; //Enter time as Seconds
	}
	
	protected void initialize() {
		count = 0;
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		count++;
	}
	protected boolean isFinished() {
		if (count < time) return false;
		return true;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
	
	
}

