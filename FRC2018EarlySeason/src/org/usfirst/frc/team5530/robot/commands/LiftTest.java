package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Lift;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class LiftTest extends Command{
	
	public LiftTest() {
		super("LiftTest");
		requires(Robot.lift);
	}
	
	
	protected void initialize() {
		Lift.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		if(OI.XBController.getAButton()) {
			Lift.Lift0.set(ControlMode.PercentOutput, (double)0.5);
		}else {
			Lift.Lift0.set(ControlMode.PercentOutput, (double)0);
		}
		
	}
	protected boolean isFinished() {
		
		return true; // maybe true?
	}
	protected void end() {
	}
	protected void interrupted() {
		
	}
	
}
