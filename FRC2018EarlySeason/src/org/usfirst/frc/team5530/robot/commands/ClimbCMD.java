package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Climb;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to lift the robot

public class ClimbCMD extends Command{
	public ClimbCMD() {
		requires(Robot.climb);
	}
	
	
	protected void initialize() {
		Climb.setFollowing();
		
	}

	protected void execute() {
		Climb.Climb0.set(ControlMode.PercentOutput, 1);//This sets both of them 
	
	}
	protected boolean isFinished() {
		if (OI.getButtonValue(3)) return false;
		return true; 
	}
	protected void end() {
		Climb.Climb0.set(0);
	}
	protected void interrupted() {
		Climb.Climb0.set(0);
	}
}
	
	
	