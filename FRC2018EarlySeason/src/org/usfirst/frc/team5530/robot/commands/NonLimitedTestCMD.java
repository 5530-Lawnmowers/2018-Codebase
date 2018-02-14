package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class NonLimitedTestCMD extends Command{
	
	WPI_TalonSRX Controller;
	double time;
	double counter = 0;
	boolean flag = false;
	
	public NonLimitedTestCMD(WPI_TalonSRX Controller, double time) {
		this.Controller = Controller;
		this.time = time*50;
		requires(Robot.intake);
		requires(Robot.climb);
		requires(Robot.drivetrain);
	}
	
	
	protected void initialize() {
		Intake.setFollowing();
		Climb.setFollowing();
	}

	protected void execute() {
		if (counter <= time) Controller.set(1);
		else if (time < counter && counter <= time*1.1) Controller.set(0);
		else if (time*1.1 < counter && counter <= time*2) Controller.set(-1);
		else {
			Controller.set(0);
			flag = true;
		}
		counter++;
		OI.buttons[5].cancelWhenPressed(this);
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		Controller.set(0);
		if (Controller.equals(Drivetrain.frontRight)) OI.buttons[5].whenPressed(Robot.FLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.frontLeft)) OI.buttons[5].whenPressed(Robot.BLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backLeft)) OI.buttons[5].whenPressed(Robot.BRDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backRight)) OI.buttons[5].whenPressed(Robot.BLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backLeft)) OI.buttons[5].whenPressed(Robot.elevatorMotor0); // intakeMotor0
//		else if (Controller.equals(Intake.Intake0)) OI.buttons[5].whenPressed(Robot.climbMotor0);
//		else OI.buttons[5].whenPressed(Robot.elevatorMotor0);
	}
	protected void interrupted() {
		Controller.set(0);
		if (Controller.equals(Drivetrain.frontRight)) OI.buttons[5].whenPressed(Robot.FLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.frontLeft)) OI.buttons[5].whenPressed(Robot.BLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backLeft)) OI.buttons[5].whenPressed(Robot.BRDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backRight)) OI.buttons[5].whenPressed(Robot.BLDriveTrainMotor);
		else if (Controller.equals(Drivetrain.backLeft)) OI.buttons[5].whenPressed(Robot.elevatorMotor0); //intakeMotor0
//		else if (Controller.equals(Intake.Intake0)) OI.buttons[5].whenPressed(Robot.climbMotor0);
//		else OI.buttons[5].whenPressed(Robot.elevatorMotor0);
	}
	
	
	
}

