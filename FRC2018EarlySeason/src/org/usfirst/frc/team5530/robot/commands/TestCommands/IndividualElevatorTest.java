package org.usfirst.frc.team5530.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IndividualElevatorTest extends Command{
	
	WPI_TalonSRX Controller;
	double counter;
	boolean isGoingUp;
	boolean isFinished;
	
	public IndividualElevatorTest(WPI_TalonSRX Controller) {
		this.Controller = Controller;
		requires(Robot.elevatorSS);
	}
	 
	
	protected void initialize() {
		counter = 0;
		isGoingUp = false;
		isFinished = false;
		System.out.println("Running Elevator Motor" + Controller.getDeviceID());
	}

	protected void execute() {
		if (ElevatorSS.elevatorSwitchTop.get() && isGoingUp && counter <= 7){ 
			Controller.set(.35);
		}else if (isGoingUp) {
			Controller.set(-.08);
			isGoingUp = false;
		}else if (ElevatorSS.elevatorSwitchBot.get() && counter < 14) {
			ArmSS.arm.set(-.08);
		}else isFinished = true;
		counter ++;
	}
	protected boolean isFinished() {
		return isFinished;
	}
	protected void end() {
		Controller.set(0);
		if (Controller.equals(ElevatorSS.Elevator0)) Robot.elevatorMotor1Test.start();
		else Robot.elevatorTest.start();
		System.out.println("End");
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

