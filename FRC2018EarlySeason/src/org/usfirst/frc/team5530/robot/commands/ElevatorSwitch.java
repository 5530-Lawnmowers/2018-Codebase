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

public class ElevatorSwitch extends Command{
	
	public static double switchHeight = 0;
	
	public ElevatorSwitch() {
		requires(Robot.elevatorSS);
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
		ElevatorSS.Elevator0.set(ControlMode.PercentOutput, 0);
	}

	protected void execute() {
		ElevatorSS.Elevator0.set(ControlMode.PercentOutput, 1); 
	}
	protected boolean isFinished() {
		if (ElevatorSS.Elevator0.getSelectedSensorPosition(0) > switchHeight) return true;						
		return false; 
	}
	protected void end() {
		ElevatorSS.Elevator0.set(.18);
	}
	protected void interrupted() {
		ElevatorSS.Elevator0.set(0);
	}
	
	
	
}