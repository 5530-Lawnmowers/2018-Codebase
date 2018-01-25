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

public class ManualArm extends Command{
	
	public ManualArm() {
		super("ManualArm");
		requires(Robot.lift);
	}
	
	
	protected void initialize() {
		Lift.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		double value = OI.stick1.getY();
		Lift.Arm.set(ControlMode.PercentOutput, value*0.5);
		if(!OI.stick1.getTrigger())
			Lift.Arm.set(ControlMode.PercentOutput, 0);
			
	}
	protected boolean isFinished() {
		
		return true; // maybe true?
	}
	protected void end() {
		
	}
	protected void interrupted() {
		Lift.Arm.set(ControlMode.PercentOutput, 0);
		
	}
	
	
	
}

