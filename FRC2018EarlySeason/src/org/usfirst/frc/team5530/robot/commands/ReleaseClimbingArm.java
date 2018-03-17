package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team5530.robot.Robot;
import org.usfirst.frc.team5530.robot.RobotMap;
import org.usfirst.frc.team5530.robot.commands.*;
import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.commands.*;


import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

public class ReleaseClimbingArm extends Command {

	double position;
	public ReleaseClimbingArm(double position){
		requires(Robot.climbSS);
		this.position = position;
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		ClimbSS.servo0.set(SmartDashboard.getNumber("ServoPos", position)); //Set servos to open position
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
