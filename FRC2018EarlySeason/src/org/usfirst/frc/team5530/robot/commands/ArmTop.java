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

public class ArmTop extends Command{
	
	double distance;
	double armPosition;
	double top = 450;
	double bot = 2600;
	public static final double ceiling = 32800;
	public static final double floor = 0;
	
	public ArmTop() {
		requires(Robot.armSS);
		
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
	}

	protected void execute() {
		if (ArmSS.potentiometer0.getValue() >= 500) ArmSS.arm.set(1);
		else ArmSS.arm.set(0);
		
	}
	protected boolean isFinished() {
//		distance = Elevator.Elevator0.getSelectedSensorPosition(0);
		return false;
	}
	protected void end() {
		ArmSS.arm.set(0);
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
	}
	
	
	
}

