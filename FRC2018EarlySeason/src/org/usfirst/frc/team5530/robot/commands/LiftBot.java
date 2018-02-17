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
import org.usfirst.frc.team5530.robot.commands.TestCommands.PotentiometerActuationTest;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class LiftBot extends Command{
	
	boolean isArmFinished;
	boolean isElevatorFinished;
	
	public LiftBot() {
		requires(Robot.elevatorSS);
		requires(Robot.armSS);
	}
	
	protected void initialize() {
		isArmFinished = false;
		isElevatorFinished = false;
		ElevatorSS.setFollowing();
		ElevatorSS.Elevator0.set(ControlMode.PercentOutput, 0);
		Timer.delay(0.1);
	}

	protected void execute() {
		ElevatorSS.Elevator0.set(ControlMode.PercentOutput, -.15); //There is a small pause after button press
		ArmSS.arm.set(-1);
		if (ArmSS.potentiometer0.getValue() >= PotentiometerActuationTest.bot) {
			 ArmSS.arm.set(0);
			 isArmFinished = true;
		}
		if (!ElevatorSS.elevatorSwitchBot.get()) {
			 ElevatorSS.Elevator0.set(0);
			 isElevatorFinished = true;
			 ElevatorSS.Elevator0.setSelectedSensorPosition(0, 0, 0);
		}
	}
	protected boolean isFinished() {
		if (isArmFinished && isElevatorFinished) {
			return true;
		}
		return false; 
	}
	protected void end() {
		ElevatorSS.Elevator0.set(0);
	}
	protected void interrupted() {
		ElevatorSS.Elevator0.set(0);
	}
	
	
	
}

