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

public class moveArm extends Command{
	
	String name;
	boolean flag = false;
	double counter;
	
	public moveArm(String name) {
		requires(Robot.armSS);
		this.name = name;
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
		counter = 0;
		flag = false;
	}

	protected void execute() {
		if (name.equalsIgnoreCase("Top") && ArmSS.potentiometer0.getValue() >= 500) {
			ArmSS.arm.set(ControlMode.PercentOutput, .00336*ArmSS.potentiometer0.getValue() - 1.856);
			if(counter < 200) counter ++;
			else {
				System.out.println("***********************************************");
				flag = true;
			}
		}
		else if ((name.equalsIgnoreCase("Bot") || name.equalsIgnoreCase("StartingPosition"))) {
			if (ArmSS.potentiometer0.getValue() <= 3300) {
				ArmSS.arm.set(-1);
			}else {
				ArmSS.arm.stopMotor();
				System.out.println("Number of Tests: " + ArmTest.count);
				flag = true;
			}
		}else System.out.println("Incorrect Parameter");
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		ArmTest.count = ArmTest.count + 0.5;
		ArmSS.arm.set(0);
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
	}
	
	
	
}

