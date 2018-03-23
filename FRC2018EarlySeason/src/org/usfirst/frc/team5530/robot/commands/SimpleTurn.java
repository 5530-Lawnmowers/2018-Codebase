package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class SimpleTurn extends Command{
	
	String direction;
	boolean flag = false;
	
	public SimpleTurn(String direction) {
		requires(Robot.drivetrainSS);
		this.direction = direction;
	}
	
	protected void initialize() {
		DrivetrainSS.frontLeft.selectProfileSlot(0, 1); //TODO set it back somewhere else
	}

	protected void execute() {
		if (direction.equalsIgnoreCase("right")) {
			DrivetrainSS.frontRight.set(ControlMode.Position, 512);
			flag = true;
		}
		else if (direction.equalsIgnoreCase("left")) {
			DrivetrainSS.frontLeft.set(ControlMode.Position, 512);
			flag = true;
		}
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		ArmSS.arm.set(0);
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
	}
	
	
	
}

