package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.ClimbSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to lift the robot

public class ReverseClimb extends Command{
	public ReverseClimb() {
		requires(Robot.climbSS);
	}
	
	
	protected void initialize() {
		ClimbSS.setFollowing();
		
	}

	protected void execute() {
		ClimbSS.Climb0.set(ControlMode.PercentOutput, SmartDashboard.getNumber("ClimbSpeed", 0.3));
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
	
	
	