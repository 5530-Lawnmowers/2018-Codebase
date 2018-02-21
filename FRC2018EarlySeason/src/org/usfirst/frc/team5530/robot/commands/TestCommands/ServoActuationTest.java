package org.usfirst.frc.team5530.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ServoActuationTest extends Command{
	
	WPI_TalonSRX Controller;
	double time;
	double counter = 0;
	boolean flag = false;
	Servo currentServo;
	
	public ServoActuationTest(Servo currentServo, double time) {
		this.currentServo = currentServo;
		this.time = time*50;
		requires(Robot.climbSS);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		if (counter <= time) currentServo.set(.33);
		else if (time < counter && counter <= time*2) currentServo.set(.66);
		else {
			currentServo.set(0);
			flag = true;
		}
		counter++;
	}
	protected boolean isFinished() {
		return flag;
	}
	protected void end() {
		currentServo.set(0);
	}
	protected void interrupted() {
		currentServo.set(0);
	}
	
	
	
}

