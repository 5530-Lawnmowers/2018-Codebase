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
	
	public IndividualElevatorTest(WPI_TalonSRX Controller) {
		this.Controller = Controller;
		requires(Robot.elevatorSS);
	}
	 
	
	protected void initialize() {
		counter = 0;
	}

	protected void execute() {
		if (counter < 50) {
			if (Controller.equals(ElevatorSS.Elevator0)) Controller.set(-.1);
			else Controller.set(.1);
		} else if (counter >= 50 && counter <= 100) {
			if (Controller.equals(ElevatorSS.Elevator0)) Controller.set(.1);
			else Controller.set(-.1);
		}
		counter++;
	}
	protected boolean isFinished() {
		if (counter > 100) return true;
		return false;
	}
	protected void end() {
		Controller.set(0);
		if (Controller.equals(ElevatorSS.Elevator0)) Robot.elevatorMotor1Test.start();
		else Robot.elevatorTest.start();
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

