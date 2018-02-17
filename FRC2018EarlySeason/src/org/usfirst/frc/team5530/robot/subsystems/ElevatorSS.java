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
public class ElevatorSS extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public static WPI_TalonSRX Elevator0  = new WPI_TalonSRX(RobotMap.E0);
	public static WPI_TalonSRX Elevator1  = new WPI_TalonSRX(RobotMap.E1);
	
	//Default Value of Limit Switch is true (open is true)
	public static DigitalInput elevatorSwitchTop = new DigitalInput(RobotMap.LS2);
	public static DigitalInput elevatorSwitchBot = new DigitalInput(RobotMap.LS3);
	
	
	
	//this method is called if no other command is called by the scheduler to use this subsystem
	
	public static void setFollowing() {
		Elevator1.set(ControlMode.Follower, (double)RobotMap.E0);
		Elevator1.setInverted(true);
		ElevatorSS.Elevator0.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		Elevator0.setSensorPhase(true);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//By defualt the Drivetrain system will call command xboxdrive by scheduler
	
	}
	
}
