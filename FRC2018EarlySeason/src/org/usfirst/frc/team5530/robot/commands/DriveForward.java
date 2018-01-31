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

import org.usfirst.frc.team5530.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class DriveForward extends Command implements PIDOutput{
	
	PIDController controller;
	double rightDriveMotorPercentOut;
	
	public double error = 4;
	static double projectedDistance = 5; //inch
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	public DriveForward() {
		super("DriveForward");
		requires(Robot.drivetrain);
		//Drivetrain.FREncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		//Drivetrain.FREncoder.reset();
		//controller = new PIDController(kP, kI, kD, kF, Drivetrain.FREncoder, this);
		controller.setOutputRange(-1.0, 1.0);
		controller.setAbsoluteTolerance(error);
	}
	
	//A method to limit an input double to the range -1.0 to 1.0
	
	protected void initialize() {
		Drivetrain.setFollowing();
		controller.setSetpoint(projectedDistance);
		SmartDashboard.putNumber("Test", projectedDistance);
		controller.enable();
	}
	
	protected void execute() {
		Drivetrain.frontRight.set(ControlMode.PercentOutput, rightDriveMotorPercentOut);
		SmartDashboard.putNumber("Right Sensor Position", Drivetrain.frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Sensor Velocity", (Drivetrain.frontRight.getSelectedSensorVelocity(0) * 600) / 4096);
		SmartDashboard.putNumber("Percent Out", rightDriveMotorPercentOut);
		//SmartDashboard.putNumber("projected distance", Drivetrain.FREncoder.getDistancePerPulse()*4096*projectedDistance);

	}
	
	protected boolean isFinished() {
		//if (Math.abs(Drivetrain.FREncoder.getDistance() - projectedDistance)<error){
		//	return true;
		//}
		//System.out.println(Drivetrain.right.getPosition());
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
	@Override
	public void pidWrite(double output) {
		rightDriveMotorPercentOut = output;
	}
}
