package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5530.robot.subsystems.Lift;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//This command turns the motors to move the arm to the top of the elevator

public class ElevatorTopCMD extends Command{
	public ElevatorTopCMD() {
		requires(Robot.lift);
	}
	
	protected void initialize() {
		Lift.setFollowing();
		Lift.Lift1.set(ControlMode.PercentOutput, 0);
		Timer.delay(0.5);
	}

	protected void execute() {
		Lift.Lift1.set(ControlMode.PercentOutput, 0.05);
		
	}
	protected boolean isFinished() {
		if (!Lift.liftSwitch0.get()) return true;
		return false; 
	}
	protected void end() {
		Lift.Lift1.set(0);
	}
	protected void interrupted() {
		Lift.Lift1.set(0);
	}
	
	
	
}

