package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.ClimbSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to lift the robot

public class Climb extends Command{
	public Climb() {
		requires(Robot.climbSS);
	}
	
	
	protected void initialize() {
		ClimbSS.setFollowing();
		
	}

	protected void execute() {
		ClimbSS.Climb0.set(ControlMode.PercentOutput, 1);//This sets both of them 
	
	}
	protected boolean isFinished() {
		if (OI.getButtonValue(3)) return false;
		return true; 
	}
	protected void end() {
		ClimbSS.Climb0.set(0);
	}
	protected void interrupted() {
		ClimbSS.Climb0.set(0);
	}
}
	
	
	