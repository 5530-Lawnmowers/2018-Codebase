package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class DriveForwardMM extends Command{
	double encodeDistance;
	int startDistanceR;
	int startDistanceL;
	double distance;
	double accelerationTime;
	double cruiseVelocity;
	double counter;
	int error = 7;
	public DriveForwardMM(double speed, double accelerationTime, double distance) { // speed is in radians per s.True is left, false is right
		super("DriveForwardMM");
		requires(Robot.drivetrainSS);
		this.accelerationTime = accelerationTime;
		cruiseVelocity = speed;
		this.distance = distance;
	}
	protected void initialize() {
		DrivetrainSS.setFollowing();
		startDistanceR = DrivetrainSS.frontRight.getSelectedSensorPosition(0);
		startDistanceL = DrivetrainSS.frontLeft.getSelectedSensorPosition(0);
		
		DrivetrainSS.frontRight.configMotionCruiseVelocity((int) -convertToTicks(cruiseVelocity), 0);
		DrivetrainSS.frontRight.configMotionAcceleration((int)-convertToTicks(cruiseVelocity / accelerationTime), 0);
		
		DrivetrainSS.frontLeft.configMotionCruiseVelocity((int) convertToTicks(cruiseVelocity), 0);
		DrivetrainSS.frontLeft.configMotionAcceleration((int)convertToTicks(cruiseVelocity / accelerationTime), 0);
		
		counter = 0;
	}
	
	double convertToTicks(double inches) {
		return 1024 * (inches / (6 * Math.PI));
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		//Switch: .1, 1.0E-6, 15
		//Scale: .09, 1.0E-8, 15		
		double proportionalL = 0.04;
		double integralL = 0;
		double derivativeL = 0;
		
		double proportionalR = 0.04;
		double integralR = 0;
		double derivativeR = 0;
		
		DrivetrainSS.frontRight.config_kP(0, proportionalR, 0);
		DrivetrainSS.frontLeft.config_kP(0, proportionalL, 0);
		DrivetrainSS.frontRight.config_kI(0, integralR, 0);
		DrivetrainSS.frontLeft.config_kI(0, integralL, 0);
		DrivetrainSS.frontRight.config_kD(0, derivativeR, 0);
		DrivetrainSS.frontLeft.config_kD(0, derivativeL, 0);
		
//		DrivetrainSS.frontRight.set(ControlMode.Position,- Math.rint(encodeDistance));
//		DrivetrainSS.frontLeft.set(ControlMode.Position, Math.rint(encodeDistance));
		
		DrivetrainSS.frontRight.set(ControlMode.MotionMagic, startDistanceR - convertToTicks(distance));
		
		DrivetrainSS.frontLeft.set(ControlMode.MotionMagic, startDistanceL + convertToTicks(distance));
		
		SmartDashboard.putNumber("Target Position", convertToTicks(distance));
		SmartDashboard.putNumber("Current Position R", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Current Position L", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
		
	}
	protected boolean isFinished() {
//		if (Math.abs(DrivetrainSS.frontRight.getSelectedSensorPosition(0) - (startDistanceR + convertToTicks(arcLength * rightRadius))) < convertToTicks(error) &&
//				Math.abs(DrivetrainSS.frontLeft.getSelectedSensorPosition(0) - (startDistanceR + convertToTicks(arcLength * leftRadius))) < convertToTicks(error)) return true;
		
		if (DrivetrainSS.frontRight.getSelectedSensorVelocity(0) <= 0 && DrivetrainSS.frontLeft.getSelectedSensorVelocity(0) <= 0) {
			if (counter > 50) return true;
			counter++;
		} else {
			counter = 0;
		}
		return false;
	}
	protected void end() {
		System.out.println("END OF QUARTER CIRCLE TURN!");
		DrivetrainSS.frontLeft.stopMotor();
		DrivetrainSS.frontRight.stopMotor();
	}
	protected void interrupted() {
		DrivetrainSS.frontLeft.stopMotor();
		DrivetrainSS.frontRight.stopMotor();
	}
	
}
