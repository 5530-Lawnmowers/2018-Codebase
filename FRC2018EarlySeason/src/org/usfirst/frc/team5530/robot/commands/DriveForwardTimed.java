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

public class DriveForwardTimed extends Command{
	double encodeDistance;
	int startDistanceR;
	int startDistanceL;
	double distance;
	double counter;
	double maxTime;
	
	public DriveForwardTimed(double distance, double time) {
		super("DriveForwardTalonBasedCMD");
		maxTime = time * 50;
		requires(Robot.drivetrainSS);
		this.distance = distance;
		double rotations = distance / (6 * Math.PI);
		this.encodeDistance = rotations * 1024;
	}
	protected void initialize() {
		DrivetrainSS.setFollowing();
		startDistanceR = DrivetrainSS.frontRight.getSelectedSensorPosition(0);
		startDistanceL = DrivetrainSS.frontLeft.getSelectedSensorPosition(0);
		if (Math.abs(distance) <= 50) {
			DrivetrainSS.frontLeft.selectProfileSlot(3, 0);
			DrivetrainSS.frontRight.selectProfileSlot(3, 0);
		} else {
			DrivetrainSS.frontLeft.selectProfileSlot(0, 0);
			DrivetrainSS.frontRight.selectProfileSlot(0, 0);
		}
		counter = 0;
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		//Switch: .1, 1.0E-6, 15
		//Scale: .09, 1.0E-8, 15
		double proportional = SmartDashboard.getNumber("Forward P Value", 0.15);
		double integral = SmartDashboard.getNumber("Forward I Value", 0.000001);
		double derivative = SmartDashboard.getNumber("Forward D Value", 0.10);
		
		DrivetrainSS.frontRight.config_kP(0, proportional, 0);
		DrivetrainSS.frontLeft.config_kP(0, proportional, 0);
		DrivetrainSS.frontRight.config_kI(0, integral, 0);
		DrivetrainSS.frontLeft.config_kI(0, integral, 0);
		DrivetrainSS.frontRight.config_kD(0, derivative, 0);
		DrivetrainSS.frontLeft.config_kD(0, derivative, 0);
		
		DrivetrainSS.frontRight.setSensorPhase(true);
		DrivetrainSS.frontRight.set(ControlMode.Position, startDistanceR - Math.rint(encodeDistance));
		DrivetrainSS.frontLeft.set(ControlMode.Position, startDistanceL + Math.rint(encodeDistance));
		
		DrivetrainSS.frontRight.config_kP(3, .25, 0);
		DrivetrainSS.frontLeft.config_kP(3, .25, 0);
		DrivetrainSS.frontRight.config_kI(3, 0, 0);
		DrivetrainSS.frontLeft.config_kI(3, 0, 0);
		DrivetrainSS.frontRight.config_kD(3, 0, 0);
		DrivetrainSS.frontLeft.config_kD(3, 0, 0);
		
		counter++;		
	}
	protected boolean isFinished() {
//		if (DrivetrainSS.frontRight.getSelectedSensorPosition())
//		if (DrivetrainSS.frontRight.getSelectedSensorVelocity(0) < 5 || DrivetrainSS.frontLeft.getSelectedSensorVelocity(0) < 5) counter++;
//		else counter = 0;
		if (maxTime > 0) {
			if (counter >= maxTime) return true;
		} else {
			if (((encodeDistance - DrivetrainSS.frontLeft.getSelectedSensorPosition(0)) < 200)) return true;
		}
		return false;
	}
	protected void end() {
		//DrivetrainSS.frontRight.set(ControlMode.Position, 325); 
	}
	protected void interrupted() {
		
	}
	
}