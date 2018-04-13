package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class ElevatorMid extends Command{
	
	double distance;
	double armPosition;
	double top = 450;
	double bot = 2600;
	
	public ElevatorMid() {
		requires(Robot.elevatorSS);
		requires(Robot.armSS);
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
	}

	protected void execute() {
		ArmSS.arm.set(ControlMode.PercentOutput, .0057*(ArmSS.potentiometer0.getValue() - ArmSS.restingHeight) + .16); 
		if (ElevatorSS.Elevator0.getSelectedSensorPosition(0) < 19000) ElevatorSS.Elevator0.set(.75);
		else ElevatorSS.Elevator0.set(.15);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
		ArmSS.arm.set(0);
		ElevatorSS.Elevator0.set(0);
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
		ElevatorSS.Elevator0.set(0);
	}
	
	
	
}

