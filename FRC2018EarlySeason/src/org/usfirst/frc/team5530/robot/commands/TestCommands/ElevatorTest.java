package org.usfirst.frc.team5530.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ElevatorTest extends Command{
	
	double time;
	double counter = 0;
	boolean finished = false;
	boolean limitHit = false;
	
	
	public ElevatorTest(double time) {
		this.time = time*50;
		requires(Robot.elevatorSS);
	}
	 
	
	protected void initialize() {
		ElevatorSS.setFollowing();
	}

	protected void execute() {
		if (!ElevatorSS.elevatorSwitchTop.get()) limitHit = true;
		if (limitHit) {
			ElevatorSS.Elevator0.set(-0.08);
			if (!ElevatorSS.elevatorSwitchBot.get()) finished = true;
		} else if (counter <= time) {
			ElevatorSS.Elevator0.set(.25);
		} else if (time < counter && counter <= time*2) {
			ElevatorSS.Elevator0.set(-0.08);
			if (!ElevatorSS.elevatorSwitchBot.get()) finished = true;
		} else finished = true;
		counter++;
		//OI.buttons[5].cancelWhenPressed(this);
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
		ElevatorSS.Elevator0.set(0);
	}
	protected void interrupted() {
		ElevatorSS.Elevator0.set(0);
	}
	
	
	
}

