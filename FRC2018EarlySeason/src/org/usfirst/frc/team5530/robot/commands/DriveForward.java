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
		double rotations = distance / (6 * Math.PI);
		this.encodeDistance = rotations * 1024;
	}
	protected void initialize() {
		DrivetrainSS.setFollowing();
		startDistanceR = DrivetrainSS.frontRight.getSelectedSensorPosition(0);
		startDistanceL = DrivetrainSS.frontLeft.getSelectedSensorPosition(0);
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
		
		DrivetrainSS.frontRight.set(ControlMode.Position,- Math.rint(encodeDistance));
		DrivetrainSS.frontLeft.set(ControlMode.Position, Math.rint(encodeDistance));
		
		SmartDashboard.putNumber("Target Position", encodeDistance);
		SmartDashboard.putNumber("Current Position", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
	}
	protected boolean isFinished() {
		if ((encodeDistance - DrivetrainSS.frontLeft.getSelectedSensorPosition(0)) < 200) return true;
		return false;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
