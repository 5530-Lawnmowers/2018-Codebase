package org.usfirst.frc.team5530.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Servo;

import org.usfirst.frc.team5530.robot.RobotMap;
import org.usfirst.frc.team5530.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;


/**
 *
 */
public class Climb extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX Climb0  = new WPI_TalonSRX(RobotMap.C0);
	public static WPI_TalonSRX Climb1  = new WPI_TalonSRX(RobotMap.C1);
	
	//public static Servo servo0 = new Servo(RobotMap.S0);
	//public static Servo servo1 = new Servo(RobotMap.S1);
			
		//this method is called if no other command is called by the scheduler to use this subsystem
	public static void setFollowing() {
		Climb1.set(ControlMode.Follower, (double)RobotMap.C0);
			
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
