package org.usfirst.frc.team5530.robot.commands.TestCommands;

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

public class NonLimitedTest extends Command{
	
	WPI_TalonSRX Controller;
	double time;
	double counter = 0;
	boolean flag = false;
	
	public NonLimitedTest(WPI_TalonSRX Controller, double time) {
		this.Controller = Controller;
		this.time = time*50;
		requires(Robot.intakeSS);
		requires(Robot.climbSS);
		requires(Robot.drivetrainSS);
	}
	
	
	protected void initialize() {
		IntakeSS.setFollowing();
		ClimbSS.setFollowing();
		System.out.println("Running: " + Controller.getDeviceID());
		flag = false;
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
		//OI.buttons[5].cancelWhenPressed(this);
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		Controller.set(0);
		if (Controller.equals(DrivetrainSS.frontRight)) Robot.FLDriveTrainMotorTest.start();
		else if (Controller.equals(DrivetrainSS.frontLeft)) Robot.BLDriveTrainMotorTest.start();
		else if (Controller.equals(DrivetrainSS.backLeft)) Robot.BRDriveTrainMotorTest.start(); 
//		else if (Controller.equals(DrivetrainSS.backRight)) Robot.climbMotor0Test.start();
//		else if (Controller.equals(DrivetrainSS.backRight)) Robot.climbMotor1Test.start();
		else if (Controller.equals(DrivetrainSS.backRight)) Robot.elevatorMotor0Test.start(); 
		else Robot.elevatorMotor0Test.start(); 
		System.out.println("EndTest");
	}
	protected void interrupted() {
		Controller.set(0);
	}
	
	
	
}

