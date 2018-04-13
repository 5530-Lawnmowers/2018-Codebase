package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.IntakeSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command runs the motors in the intake system to possess the cube, and then continues to run the motors (to secure the cube) until the deliver command is used

public class IntakeOff extends Command{
	public IntakeOff() {
		requires(Robot.intakeSS);
	}
	protected void initialize() {
		IntakeSS.setFollowing();
	}
	protected void execute() {
		IntakeSS.Intake0.set(ControlMode.PercentOutput, 0);
		IntakeSS.intakeOn = false;
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		IntakeSS.Intake0.set(0);
		IntakeSS.intakeOn = false;
	}
	protected void interrupted() {
		IntakeSS.Intake0.set(0);
		IntakeSS.intakeOn = false;
	}
	
	
	
}

