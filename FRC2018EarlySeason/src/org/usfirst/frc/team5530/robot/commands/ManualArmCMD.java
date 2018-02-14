package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Arm;
import org.usfirst.frc.team5530.robot.subsystems.Elevator;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//Disclaimer - do not read both comments, unless you are confused
//In this command, when the joystick trigger is held, the joystick will move the arm on top of the lift (Robert's lame version)
//This command allows the driver to hold the joystick trigger while controlling the joystick to move the arm on top of the lift (Lawrence's version)

public class ManualArmCMD extends Command{
	
	public ManualArmCMD() {
		super("ManualArmCMD");
		requires(Robot.arm);
	}
	
	protected void initialize() {
		Elevator.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		double value = OI.stick1.getY();
		Arm.arm.set(ControlMode.PercentOutput, -value);
		if(!OI.stick1.getTrigger())
			Arm.arm.stopMotor();
	}
	protected boolean isFinished() {
		if (OI.getButtonValue(0)) return false;
		return true;
	}
	protected void end() {
		Arm.arm.set(ControlMode.PercentOutput, 0);
		
	}
	protected void interrupted() {
		Arm.arm.set(ControlMode.PercentOutput, 0);
		
	}
	
	
	
}

