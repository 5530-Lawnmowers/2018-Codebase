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

public class ArmMid extends Command{
	
	public ArmMid() {
		requires(Robot.armSS);
		
	}
	
	protected void initialize() {
	}

	protected void execute() {
		ArmSS.arm.set(1);
		
		
	}
	protected boolean isFinished() {
//		distance = Elevator.Elevator0.getSelectedSensorPosition(0);
		if (ArmSS.potentiometer0.getValue() >= 1500) return false;
		return true;
	}
	protected void end() {
		ArmSS.arm.set(0);
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
	}
	
	
	
}

