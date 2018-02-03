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
//Disclaimer - do not read both comments, unless you are confused
//In this command, when the joystick trigger is held, the joystick will move the arm on top of the lift (Robert's lame version)
//This command allows the driver to hold the joystick trigger while controlling the joystick to move the arm on top of the lift (Lawrence's version)

public class IntakeCube extends Command{
	public IntakeCube() {
		super("IntakeCube");
		requires(Robot.intake);
		
	}
	
	
	protected void initialize() {
		Intake.setFollowing();
		
	}

	protected void execute() {
		Intake.Intake0.set(ControlMode.PercentOutput, -1);//This sets both of them 
	
	}
	protected boolean isFinished() {
		if (!Intake.intakeSwitch0.get() || !Intake.intakeSwitch1.get()) return true;
		return false;
	}
	protected void end() {
		Intake.Intake0.set(ControlMode.PercentOutput, -.2); //This might trip the breaker because of the high voltage use
	}
	protected void interrupted() {
		Intake.Intake0.set(0);
		
	}
	
	
	
}

