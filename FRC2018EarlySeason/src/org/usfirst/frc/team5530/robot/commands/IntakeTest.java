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

public class IntakeTest extends Command{
	
	public IntakeTest() {
		super("IntakeTest");
		requires(Robot.intake);
	}
	
	
	protected void initialize() {
		Intake.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		if(OI.XBController.getBumper(Hand.kLeft)) {
			Intake.Intake0.set(ControlMode.PercentOutput, (double)0.5);
		}else if(OI.XBController.getBumper(Hand.kRight)) {
			Intake.Intake0.set(ControlMode.PercentOutput, (double)-0.5);
		}else {
			Intake.Intake0.set(ControlMode.PercentOutput, (double)0);
		}
		
	}
	protected boolean isFinished() {
		
		return true;
	}
	protected void end() {
	}
	protected void interrupted() {
		
	}
	
}
