package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//Disclaimer - do not read both comments, unless you are confused
//In this command, when the joystick trigger is held, the joystick will move the arm on top of the lift (Robert's lame version)
//This command allows the driver to hold the joystick trigger while controlling the joystick to move the arm on top of the lift (Lawrence's version)

public class ManualArm extends Command{
	
	public ManualArm() {
		super("ManualArmCMD");
		requires(Robot.armSS);
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		double value = OI.stick1.getY();
		if(ArmSS.potentiometer0.getValue() > 500 && ArmSS.potentiometer0.getValue() < 3700)
			ArmSS.arm.set(ControlMode.PercentOutput, value);
		else if(ArmSS.potentiometer0.getValue() <= 500){
			if (value < 0) ArmSS.arm.set(ControlMode.PercentOutput, value);
			else ArmSS.arm.set(0);
		}else if(ArmSS.potentiometer0.getValue() >= 3700) {
			if (value > 0) ArmSS.arm.set(ControlMode.PercentOutput, value);
			else ArmSS.arm.set(0);
		}
		if(!OI.stick1.getTrigger())
			ArmSS.arm.stopMotor();
			
	}
	protected boolean isFinished() {
		if (OI.getButtonValue(0)) return false;
		return true;
	}
	protected void end() {
		ArmSS.arm.set(ControlMode.PercentOutput, 0);
		
	}
	protected void interrupted() {
		ArmSS.arm.set(ControlMode.PercentOutput, 0);
		
	}
	
	
	
}

