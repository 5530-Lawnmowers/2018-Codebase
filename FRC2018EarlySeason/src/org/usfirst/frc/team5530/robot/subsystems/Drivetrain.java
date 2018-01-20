package org.usfirst.frc.team5530.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team5530.robot.RobotMap;
import org.usfirst.frc.team5530.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;


/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX frontRight  = new WPI_TalonSRX(RobotMap.FR);
	public static WPI_TalonSRX frontLeft  = new WPI_TalonSRX(RobotMap.FL);
	public static WPI_TalonSRX backRight  = new WPI_TalonSRX(RobotMap.BR);
	public static WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.BL);
	
	//a method to set the second motor of the same side to follow (do the same thing as) the first motor of that side
	public static void setFollowing() {
		backRight.set(ControlMode.Follower, (double)RobotMap.FR);
		
		backLeft.set(ControlMode.Follower, (double)RobotMap.FR);
			
	}
		//this method is called if no other command is called by the scheduler to use this subsystem	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new XboxDrive());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
