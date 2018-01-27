package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class DriveForward extends Command{
	
	public DriveForward() {
		super("DriveForward");
		requires(Robot.drivetrain);
	}
	
	//A method to limit an input double to the range -1.0 to 1.0
	public double limit(double prelimNumber){
		if(prelimNumber >= 1.0){
			return 1.0;
					
		}else if(prelimNumber <= -1.0){
			
			return -1.0;
		}else if(prelimNumber < 1.0 && prelimNumber >-1.0){
			
			return prelimNumber;
		}else{
			
			return 0;
		}
		
		
	}


	
	protected void initialize() {
		Drivetrain.setFollowing();
	}
	protected void execute() {
		Drivetrain.frontRight.set(ControlMode.Position, 5*4096);

	}
	protected boolean isFinished() {
		
		return true; // maybe true?
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
