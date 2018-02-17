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

public class Intake extends Command{
	public Intake() {
		super("IntakeCMD");
		requires(Robot.intakeSS);	
	}
	protected void initialize() {
		IntakeSS.setFollowing();
	}
	protected void execute() {
		IntakeSS.Intake0.set(ControlMode.PercentOutput, 1);
	}
	protected boolean isFinished() {
		//if (!Intake.intakeSwitch0.get() || !Intake.intakeSwitch1.get()) return true;
		//return false;
		return false;
	}
	protected void end() {
		IntakeSS.Intake0.set(0);
	}
	protected void interrupted() {
		IntakeSS.Intake0.set(0);
	}
	
	
	
}

