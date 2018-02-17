package org.usfirst.frc.team5530.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team5530.robot.RobotMap;
import org.usfirst.frc.team5530.robot.commands.*;


import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;


/**
 *
 */
public class IntakeSS extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX Intake0 = new WPI_TalonSRX(RobotMap.I0);
	public static WPI_TalonSRX Intake1 = new WPI_TalonSRX(RobotMap.I1);
	
	//Default Value of Limit Switch is true (open is true)
	public static DigitalInput intakeSwitch0 = new DigitalInput(RobotMap.LS0);
	public static DigitalInput intakeSwitch1 = new DigitalInput(RobotMap.LS1);
	
	
		//this method is called if no other command is called by the scheduler to use this subsystem
	public static void setFollowing() {
		Intake1.set(ControlMode.Follower, (double)RobotMap.I0);
		Intake1.setInverted(true);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
