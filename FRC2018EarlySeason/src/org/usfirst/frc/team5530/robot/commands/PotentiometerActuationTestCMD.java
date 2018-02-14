//package org.usfirst.frc.team5530.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.AnalogInput;
//
//
//import org.usfirst.frc.team5530.robot.subsystems.*;
//import org.usfirst.frc.team5530.robot.*;
//
//import com.ctre.phoenix.motorcontrol.*;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//
//public class PotentiometerActuationTestCMD extends Command{
//	
//	WPI_TalonSRX Controller;
//	double time;
//	double counter = 0;
//	boolean flag = false;
//	AnalogInput currentPotentiometer;
//	
//	
//	public PotentiometerActuationTestCMD(WPI_TalonSRX Controller, AnalogInput currentPotentiometer, double time) {
//		this.currentPotentiometer = currentPotentiometer;
//		this.time = time*50;
//		requires(Robot.lift);
//	}
//	
//	protected void initialize() {
//		
//	}
//
//	protected void execute() {
//		if (counter <= time) {
//			
//		}
//		else if (time < counter && counter <= time*1.1) {
//			Controller.set(0);
//			SmartDashboard.getBoolean("Potentiometer Accurate: ", currentPotentiometer.equals(Controller));
//		}
//		else if (time*1.1 < counter && counter <= time*2) {
//			Controller.set(-1);
//			SmartDashboard.getBoolean("Potentiometer Accurate: ", currentPotentiometer.equals(Controller));
//		}
//		else {
//			Controller.set(0);
//			SmartDashboard.getBoolean("Potentiometer Accurate: ", currentPotentiometer.equals(Controller));
//			flag = true;
//		}
//		counter++;
//	}
//	protected boolean isFinished() {
//		return flag;
//	}
//	protected void end() {
//		Controller.set(0);
//	}
//	protected void interrupted() {
//		Controller.set(0);
//	}
//	
//	
//	
//}
//
