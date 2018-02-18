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

public class ManualElevator extends Command{
	
	public ManualElevator() {
		super("ManualElevator");
		requires(Robot.elevatorSS);
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		double value = OI.stick1.getY();
		if(ElevatorSS.elevatorSwitchBot.get() && ElevatorSS.elevatorSwitchTop.get()) ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.5);
		if(!OI.stick1.getTrigger()) ElevatorSS.Elevator0.stopMotor();
		System.out.println("Elevator Encoder: " +  ElevatorSS.Elevator0.getSelectedSensorPosition(0));
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

