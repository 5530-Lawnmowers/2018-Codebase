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
import org.usfirst.frc.team5530.robot.commands.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class ArmModular extends Command{
	
	String position;
	boolean flag = false;
	double counter;
	
	public ArmModular(String name) {
		requires(Robot.armSS);
		position = name;
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
		counter = 0;
		flag = false;
	}

	protected void execute() {
		if (position.equalsIgnoreCase("Top")) {
			ArmSS.arm.set(ControlMode.PercentOutput, .0057*(ArmSS.potentiometer0.getValue() - ArmSS.maxArmHeight) + .16); 
			if(counter < 500) counter ++;
			else flag = true;
		}else if (position.equalsIgnoreCase("Bot")) {
			if (ArmSS.potentiometer0.getValue() <= 2500) {
				ArmSS.arm.set(-1);
			}else {
				ArmSS.arm.stopMotor();
				flag = true;
			}
		}else if(position.equalsIgnoreCase("StartingPosition")) {
			if (ArmSS.potentiometer0.getValue() <= 3300) { //TODO Set this arbitrary value to be correct
				ArmSS.arm.set(-1);
			}else {
				ArmSS.arm.stopMotor();
				flag = true;
			}
		}else if(position.equalsIgnoreCase("Mid")) {
			ArmSS.arm.set(1);
			if (ArmSS.potentiometer0.getValue() >= 1500) flag = true;
		}
		else System.out.println("Incorrect Parameter");
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

