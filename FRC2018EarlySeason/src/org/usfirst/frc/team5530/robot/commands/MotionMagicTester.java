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

public class MotionMagicTester extends Command{
	double encodeDistance;
	int startDistanceR;
	int startDistanceL;
	double distance;
	double velocity;
	double acceleration;
	double rightP = 0.02; // OG: 0.072 OG#2: 0.04
	double rightI = .000004; //We don't use this, yet
	double leftP = 0.2; // OG: 0.228 OG#2: 0.25
	double leftI = .000004;
	double counter;
	int error = 7;
	public MotionMagicTester(double distance, double velocity, double acceleration) { // speed is in radians per s.
		super("DriveForwardTalonBasedCMD");
		requires(Robot.drivetrainSS);
		this.distance = distance;
		this.velocity =velocity / 10;
		this.acceleration = acceleration / 10;
	}
	protected void initialize() {
		DrivetrainSS.setFollowing();
		
		DrivetrainSS.frontRight.set(ControlMode.MotionMagic, 0);
		DrivetrainSS.frontLeft.set(ControlMode.MotionMagic, 0);
		
		startDistanceR = DrivetrainSS.frontRight.getSelectedSensorPosition(0);
		startDistanceL = DrivetrainSS.frontLeft.getSelectedSensorPosition(0);
		
		DrivetrainSS.frontRight.configMotionCruiseVelocity((int) -convertInchesToTicks(velocity*1.35), -1);
		DrivetrainSS.frontRight.configMotionAcceleration((int)-convertInchesToTicks(acceleration*2), -1);
		
		DrivetrainSS.frontLeft.configMotionCruiseVelocity((int) convertInchesToTicks(velocity), -1);
		DrivetrainSS.frontLeft.configMotionAcceleration((int)convertInchesToTicks(acceleration), -1);
		
		//Switch: .1, 1.0E-6, 15
		//Scale: .09, 1.0E-8, 15		
		double proportionalL = leftP;
		double integralL = leftI;
		double derivativeL = 0;
		
		double proportionalR = rightP;
		double integralR = rightI;
		double derivativeR = 0;
		
		DrivetrainSS.frontRight.config_kP(0, proportionalR, 0);
		DrivetrainSS.frontLeft.config_kP(0, proportionalL, 0);
		DrivetrainSS.frontRight.config_kI(0, integralR, 0);
		DrivetrainSS.frontLeft.config_kI(0, integralL, 0);
		DrivetrainSS.frontRight.config_kD(0, derivativeR, 0);
		DrivetrainSS.frontLeft.config_kD(0, derivativeL, 0);
		
		counter = 0;
	}
	
	double convertInchesToTicks(double inches) {
		return 1024 * (inches / (6 * Math.PI));
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		
		
//		DrivetrainSS.frontRight.set(ControlMode.Position,- Math.rint(encodeDistance));
//		DrivetrainSS.frontLeft.set(ControlMode.Position, Math.rint(encodeDistance));

		DrivetrainSS.frontLeft.set(ControlMode.MotionMagic, startDistanceL + convertInchesToTicks(distance));
		
		DrivetrainSS.frontRight.set(ControlMode.MotionMagic, startDistanceR - convertInchesToTicks(distance*1.35));
		
		SmartDashboard.putNumber("Target Position R", startDistanceR - convertInchesToTicks(distance));
		SmartDashboard.putNumber("Target Position L", startDistanceL + convertInchesToTicks(distance));
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
