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
	double distance;
	

	PIDController controller;
	double rightDriveMotorPercentOut;
	double leftDriveMotorPercentOut;
	
	public double error = 4;
	static double projectedDistance = 20; //inch
	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	public DriveForward() {
		super("DriveForward");
		requires(Robot.drivetrain);
		Drivetrain.FREncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		Drivetrain.FREncoder.reset();
		controller = new PIDController(kP, kI, kD, kF, Drivetrain.FREncoder, this);
		controller.setOutputRange(-1.0, 1.0);
		controller.setAbsoluteTolerance(error);
		controller.setContinuous(true);
	}
	
	//A method to limit an input double to the range -1.0 to 1.0
	public double limit(double prelimNumber){
		if(prelimNumber >= 1.0){
			return 1.0;
					
		}else if(prelimNumber <= -1.0){
			
			return -1.0;
		}else if(prelimNumber < 1.0 && prelimNumber >-1.0){
			
			return prelimNumber;
		}else{
			
			return 0;
		}
		
		
	}
	
	protected void initialize() {
		Drivetrain.setFollowing();
		controller.setSetpoint(Drivetrain.FREncoder.getDistance() + projectedDistance);
	}
	protected void execute() {
		Drivetrain.frontRight.set(ControlMode.PercentOutput, rightDriveMotorPercentOut);
		Drivetrain.frontLeft.set(ControlMode.PercentOutput, leftDriveMotorPercentOut);
		SmartDashboard.putNumber("Right Sensor Position", Drivetrain.FREncoder.getDistance());
		SmartDashboard.putNumber("Right Sensor Velocity", Drivetrain.FREncoder.getRate());

	}
	protected boolean isFinished() {
		if (Math.abs(Drivetrain.FREncoder.getDistance() - projectedDistance)<error){
			return true;
		}
		//System.out.println(Drivetrain.right.getPosition());
		return false;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		rightDriveMotorPercentOut = output;
		leftDriveMotorPercentOut = output;
	}
}
