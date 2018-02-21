//package org.usfirst.frc.team5530.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDOutput;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.kauailabs.navx.frc.AHRS;
//
//import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
//import org.usfirst.frc.team5530.robot.*;
//
//public class Turn extends Command implements PIDOutput{
//	
//	PIDController controller;
//	double rotateToAngleRate;
//	
//	double targetAngle;
//	AHRS ahrs;
//	double initialAngle;
//	
//	public double error = 4;
//	static double kP;
//	static double kI;
//	static double kD;
//	static double kF;
//	
//	public Turn(double angle) {
//		super("TurnCMD");
//		targetAngle = angle;
//		requires(Robot.drivetrainSS);
//		//Drivetrain.FREncoder.setPIDSourceType(PIDSourceType.kDisplacement);
//		//Drivetrain.FREncoder.reset();
//		//controller = new PIDController(kP, kI, kD, kF, Drivetrain.FREncoder, this);
//		
//		try {
//			ahrs = new AHRS(SPI.Port.kMXP);
//		} catch (RuntimeException ex){
//			DriverStation.reportError("Error instantiating navX-MXP: " + ex.getMessage(), true);
//		}
//	}
//	
//	
//	//A method to limit an input double to the range -1.0 to 1.0
//	
//	protected void initialize() {
//		System.out.println("EXECUTE BEFORE");
//		ahrs.reset();
//		System.out.println("ECEXUTE AFTER");
//		DrivetrainSS.setFollowing();
//		initialAngle = ahrs.getAngle();
//		System.out.println(initialAngle);
//		kP = 0.01;
//		kI = 0;
//		kD = 0;
//		controller = new PIDController(kP, kI, kD, kF, ahrs, this);
//		controller.reset();
//		controller.setInputRange(-180f, 180f);
//		controller.setOutputRange(-1.0, 1.0);
//		controller.setAbsoluteTolerance(error);
//		controller.setContinuous();
//		controller.setSetpoint(initialAngle + targetAngle);
//		controller.enable();
//	}
//	
//	protected void execute() {
//		kP = .00025 * (Math.abs(targetAngle)-ahrs.getAngle() - 0.01);
//		controller.setP(kP);
//		SmartDashboard.putNumber("CurrentAngle", ahrs.getAngle());
//		SmartDashboard.putNumber("Target", initialAngle + targetAngle);
//		DrivetrainSS.frontRight.set(ControlMode.PercentOutput, rotateToAngleRate);
//		DrivetrainSS.frontLeft.set(ControlMode.PercentOutput, rotateToAngleRate);
//	}
//	
//	protected boolean isFinished() {
//		if(controller.onTarget()) return true;
//		return false;
//	}
//	
//	protected void end() {
//		System.out.println(ahrs.getAngle());
//		System.out.println("End");
//		DrivetrainSS.frontRight.stopMotor();
//		DrivetrainSS.frontLeft.stopMotor();
//		controller.disable();
//	}
//	
//	protected void interrupted() {
//		DrivetrainSS.frontLeft.stopMotor();
//		DrivetrainSS.frontRight.stopMotor();
//		controller.reset();
//	}
//	
//	@Override
//	public void pidWrite(double output) {
//		rotateToAngleRate = output;
//	}
//}
