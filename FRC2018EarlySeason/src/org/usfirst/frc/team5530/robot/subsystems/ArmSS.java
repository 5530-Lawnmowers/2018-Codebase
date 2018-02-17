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
public class ArmSS extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX arm = new WPI_TalonSRX(RobotMap.A0);
	
	public static AnalogInput potentiometer0 = new AnalogInput(RobotMap.P0);
	
	//this method is called if no other command is called by the scheduler to use this subsystem
	
	public static void setFollowing() {
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
