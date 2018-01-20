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
public class Lift extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX Lift0  = new WPI_TalonSRX(RobotMap.L0);
	public static WPI_TalonSRX Lift1  = new WPI_TalonSRX(RobotMap.L1);
	public static WPI_TalonSRX Arm  = new WPI_TalonSRX(RobotMap.L2);
		//this method is called if no other command is called by the scheduler to use this subsystem
	
	public static void setFollowing() {
		Lift1.set(ControlMode.Follower, (double)RobotMap.L0);
			
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
