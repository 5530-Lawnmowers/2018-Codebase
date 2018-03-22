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

public class TurnArc extends Command{
	double encodeDistance;
	int startDistanceR;
	int startDistanceL;
	double distance;
	double turnRadius;
	double circumference;
	double rightRadius;
	double leftRadius;
	double angularVelocity;
	double accelerationTime;
	double arcLength;
	double insideP = 0.02; // OG: 0.072 OG#2: 0.04 || 0.02
	double insideI = 0; //We don't use this, yet || 0.000004
	double outsideP = 0.02; // OG: 0.228 OG#2: 0.25 || 0.5
	double outsideI = 0; // || .000025
	double trackDistance = 22.75;
	double leftP;
	double rightP;
	double counter;
	int error = 7;
	public TurnArc(double turnRadius, double arcLength, double speed, double accelerationTime, String direction) { // speed is in radians per s.
		super("DriveForwardTalonBasedCMD");
		requires(Robot.drivetrainSS);
		this.turnRadius = turnRadius;
		double innerRadius = turnRadius - trackDistance/2;
		double outerRadius = turnRadius + trackDistance/2;
		this.accelerationTime = accelerationTime;
		angularVelocity = speed/10;
		this.arcLength = arcLength;
		if(direction.equals("left")) {
			rightRadius = outerRadius;
			leftRadius = innerRadius;
			leftP = insideP;
			rightP = outsideP;
		}else if (direction.equals("right")) {
			rightRadius = innerRadius;
			leftRadius = outerRadius;
			leftP = outsideP;
			rightP = insideP;
		}else DriverStation.reportError("Ya fucked up mate", true);		
	}
	protected void initialize() {
		DrivetrainSS.setFollowing();
		startDistanceR = DrivetrainSS.frontRight.getSelectedSensorPosition(0);
		startDistanceL = DrivetrainSS.frontLeft.getSelectedSensorPosition(0);
		
		DrivetrainSS.frontRight.configMotionCruiseVelocity((int) -convertToTicks(rightRadius * angularVelocity), 0);
		DrivetrainSS.frontRight.configMotionAcceleration((int) -convertToTicks(rightRadius * angularVelocity / accelerationTime), 0);
		
		DrivetrainSS.frontLeft.configMotionCruiseVelocity((int) convertToTicks(leftRadius * angularVelocity), 0);
		DrivetrainSS.frontLeft.configMotionAcceleration((int)convertToTicks(leftRadius * angularVelocity / accelerationTime), 0);
		
		counter = 0;
	}
	
	double convertToTicks(double inches) {
		return 1024 * (inches / (6 * Math.PI));
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		//Switch: .1, 1.0E-6, 15
		//Scale: .09, 1.0E-8, 15		
		double proportionalL = leftP;
		double integralL = outsideI;
		double derivativeL = 0;
		
		double proportionalR = rightP;
		double integralR = insideI;
		double derivativeR = 0;
		
		DrivetrainSS.frontRight.config_kP(0, proportionalR, 0);
		DrivetrainSS.frontLeft.config_kP(0, proportionalL, 0);
		DrivetrainSS.frontRight.config_kI(0, integralR, 0);
		DrivetrainSS.frontLeft.config_kI(0, integralL, 0);
		DrivetrainSS.frontRight.config_kD(0, derivativeR, 0);
		DrivetrainSS.frontLeft.config_kD(0, derivativeL, 0);
		
//		DrivetrainSS.frontRight.set(ControlMode.Position,- Math.rint(encodeDistance));
//		DrivetrainSS.frontLeft.set(ControlMode.Position, Math.rint(encodeDistance));
		
		DrivetrainSS.frontRight.set(ControlMode.MotionMagic, startDistanceR - convertToTicks(arcLength * rightRadius));
		
		DrivetrainSS.frontLeft.set(ControlMode.MotionMagic, startDistanceL + convertToTicks(arcLength * leftRadius));
		
//		SmartDashboard.putNumber("Target Position R", startDistanceR - convertToTicks(arcLength * rightRadius));
//		SmartDashboard.putNumber("Target Position L", startDistanceL + convertToTicks(arcLength * leftRadius));
//		SmartDashboard.putNumber("Current Position R", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
//		SmartDashboard.putNumber("Current Position L", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
		
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
