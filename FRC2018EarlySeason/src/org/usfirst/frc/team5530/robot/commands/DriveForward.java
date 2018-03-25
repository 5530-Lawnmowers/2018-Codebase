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

public class DriveForward extends Command{
	double encodeDistance;
	int startDistanceR;
	int startDistanceL;
	double distance;
	
	public DriveForward(double distance) {
		super("DriveForwardTalonBasedCMD");
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
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		//Switch: .1, 1.0E-6, 15
		//Scale: .09, 1.0E-8, 15
//		double proportional = SmartDashboard.getNumber("Forward P Value", 0.15);
//		double integral = SmartDashboard.getNumber("Forward I Value", 0.000001);
//		double derivative = SmartDashboard.getNumber("Forward D Value", 0.10);
//		
		DrivetrainSS.frontRight.setSensorPhase(true);
		DrivetrainSS.frontRight.set(ControlMode.Position, startDistanceR - Math.rint(encodeDistance));
		DrivetrainSS.frontLeft.set(ControlMode.Position, startDistanceL + Math.rint(encodeDistance));
		
		DrivetrainSS.frontLeft.selectProfileSlot(0, 0); 
		DrivetrainSS.frontRight.selectProfileSlot(0, 0);
		
	}
	protected boolean isFinished() {
		if (((encodeDistance - DrivetrainSS.frontLeft.getSelectedSensorPosition(0)) < 200)) return true;
		return false;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
