package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.ArmSS;
import org.usfirst.frc.team5530.robot.subsystems.ElevatorSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
//Disclaimer - do not read both comments, unless you are confused
//In this command, when the joystick trigger is held, the joystick will move the arm on top of the lift (Robert's lame version)
//This command allows the driver to hold the joystick trigger while controlling the joystick to move the arm on top of the lift (Lawrence's version)


public class ManualElevator extends Command{
	boolean latch;
	double counter;
	double top;
	public ManualElevator() {
		super("ManualElevator");
		requires(Robot.elevatorSS);
	}
	
	protected void initialize() {
		ElevatorSS.setFollowing();
		latch = false;
		counter = 0;
		top = 0;
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		double value = OI.stick1.getY();
		if(ElevatorSS.elevatorSwitchBot.get() && ElevatorSS.elevatorSwitchTop.get()){ 				//If in the middle
			if (latch && ElevatorSS.Elevator0.getSelectedSensorPosition(0) > (top - 10000)) {			//And it was at the top and hasn't passed 1000 ticks below the top
				if (value < 0) ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.1);			//Only accept values that go down
				else ElevatorSS.Elevator0.set(.18);
			}else if(latch) {																			//And it was at the top and has just passed more than 1000 ticks below the top
				latch = false;																				//We are now free to move in any direction
				ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.5);
			}else {																						//And we were not recently at the top
				if (value > 0)ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.5);				//If the value is downward slow it down so it doesn't drop too fast
				else ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.1);
			}
			
		}else if(!ElevatorSS.elevatorSwitchBot.get()){												 //If at the bottom
			if (value > 0) ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.5);			//Only allow values that go up
			else ElevatorSS.Elevator0.set(0);
		}else if(!ElevatorSS.elevatorSwitchTop.get()) { 											//If at the Top
			latch = true;																				//We can now only move up if we go down more that 1000 ticks
			top = ElevatorSS.Elevator0.getSelectedSensorPosition(0);									//Reset the top encoder value
			if (value < 0) ElevatorSS.Elevator0.set(ControlMode.PercentOutput, value * 0.1);			//Only accept values that go down
			else ElevatorSS.Elevator0.set(.18);
		}
		if(!OI.getButtonValue(1)) ElevatorSS.Elevator0.stopMotor();									//If the trigger was released set the motors to 0
		System.out.println("Elevator Encoder: " +  ElevatorSS.Elevator0.getSelectedSensorPosition(0));
		System.out.println(value * 0.5);
	}
	protected boolean isFinished() {
		if (OI.getButtonValue(1)) return false;	
		return true;
	}
	protected void end() {
		ArmSS.arm.set(ControlMode.PercentOutput, 0);
		
	}
	protected void interrupted() {
		ArmSS.arm.set(ControlMode.PercentOutput, 0);
		
	}
	
	
	
}

