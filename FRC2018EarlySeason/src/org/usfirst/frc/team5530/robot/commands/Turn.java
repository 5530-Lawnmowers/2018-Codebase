package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.*;

public class Turn extends Command implements PIDOutput{
	
	PIDController controller;
	double rotateToAngleRate;
	
	double targetAngle;
	AHRS ahrs;
	double initialAngle;
	
	public double error = 4;
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.08;
	static final double kF = 0.00;
	
	public Turn() {
		super("TurnCMD");
		requires(Robot.drivetrainSS);
		//Drivetrain.FREncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		//Drivetrain.FREncoder.reset();
		//controller = new PIDController(kP, kI, kD, kF, Drivetrain.FREncoder, this);
		
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex){
			DriverStation.reportError("Error instantiating navX-MXP: " + ex.getMessage(), true);
		}
		
		controller = new PIDController(kP, kI, kD, kF, ahrs, this);
		controller.setInputRange(-180f, 180f);
		controller.setOutputRange(-1.0, 1.0);
		controller.setAbsoluteTolerance(error);
		controller.setContinuous();
		
	}
	
	
	//A method to limit an input double to the range -1.0 to 1.0
	
	protected void initialize() {
		DrivetrainSS.setFollowing();
		targetAngle = 90; //SmartDashboard.getNumber("Turn Angle", 0);
		controller.setSetpoint(ahrs.getAngle() + targetAngle);
		controller.enable();
		initialAngle = ahrs.getAngle();
	}
	
	protected void execute() {
		System.out.println("RotateRate: " + rotateToAngleRate);
		DrivetrainSS.frontRight.set(ControlMode.PercentOutput, rotateToAngleRate);
		DrivetrainSS.frontLeft.set(ControlMode.PercentOutput, rotateToAngleRate);
	}
	
	protected boolean isFinished() {
		if (Math.abs(ahrs.getAngle() - (initialAngle + targetAngle)) < error) return true;
		return false;
	}
	
	protected void end() {
		DrivetrainSS.frontRight.stopMotor();
		DrivetrainSS.frontLeft.stopMotor();
	}
	
	protected void interrupted() {
		DrivetrainSS.frontLeft.stopMotor();
		DrivetrainSS.frontRight.stopMotor();
	}
	
	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
