package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Intake;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command runs the motors in the intake system to possess the cube, and then continues to run the motors (to secure the cube) until the deliver command is used

public class IntakeCMD extends Command{
	public IntakeCMD() {
		super("IntakeCMD");
		requires(Robot.intake);	
	}
	protected void initialize() {
		Intake.setFollowing();	
	}
	protected void execute() {
		Intake.Intake0.set(ControlMode.PercentOutput, -1);//This sets both of them 
	}
	protected boolean isFinished() {
		//if (!Intake.intakeSwitch0.get() || !Intake.intakeSwitch1.get()) return true;
		//return false;
		return false;
	}
	protected void end() {
		Intake.Intake0.set(ControlMode.PercentOutput, -.2); //This might trip the breaker because of the high voltage use
	}
	protected void interrupted() {
		Intake.Intake0.set(0);
	}
	
	
	
}

