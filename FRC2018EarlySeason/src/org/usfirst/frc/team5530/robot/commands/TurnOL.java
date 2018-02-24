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

public class TurnOL extends Command{
	
	double counter;
	String direction;
	
	public TurnOL(String direction) {
		requires(Robot.drivetrainSS);
		this.direction = direction;
	}
	
	protected void initialize() {
		DrivetrainSS.setFollowing();
		counter = 0;
	}

	protected void execute() {
		if (direction.equals("right")) {
			DrivetrainSS.frontLeft.set(0.75);
			DrivetrainSS.frontRight.set(0.75);
		} else if (direction.equals("left")){
			DrivetrainSS.frontLeft.set(-0.75);
			DrivetrainSS.frontRight.set(-0.75);
		}
		counter++;
	}
	protected boolean isFinished() {
		if (counter > 33) return true;
		return false;
	}
	protected void end() {
		DrivetrainSS.frontRight.set(0);
		DrivetrainSS.frontLeft.set(0);
		System.out.println("END OF TURNOL");
	}
	protected void interrupted() {
		DrivetrainSS.frontRight.set(0);
		DrivetrainSS.frontLeft.set(0);
	}
	
	
	
}