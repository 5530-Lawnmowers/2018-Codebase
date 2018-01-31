package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

public class XboxDrive extends Command{
	
	public XboxDrive() {
		super("XboxDrive");
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
	//get xAxis value of Xbox joystick; argument is stick side
	public double getStickHorizontal(char side){
		if(side == 'r'){
			return limit(OI.XBController.getX(Hand.kRight));
		
		}else if(side == 'l'){
			return limit(OI.XBController.getX(Hand.kLeft));
			
		}else{
			return 0;
		}
	}
	//get Trigger values; arguement is trigger side
	public double getTriggerValue(char side){
		if(side == 'r'){
			return OI.XBController.getTriggerAxis(Hand.kRight);
		
		}else if(side == 'l'){
			return OI.XBController.getTriggerAxis(Hand.kLeft);
			
		}else{
			return 0;
			
		}
	}
	//Calculates right speed based on controller output
		public double XBControllerR(double lStick, double rTrigger, double lTrigger) {
			//speed of left side = amount Accelerator is pushed down minus
			//amount Deccelerator is pushed down - lateral input from left Joystick
			return rTrigger - lTrigger - lStick;
		}
		
		//Calculates left speed based on Controller output
		public double XBControllerL(double lStick, double rTrigger, double lTrigger){
			//speed of left side = amount Accelerator is pushed down minus
			//amount Deccelerator is pushed down + lateral input from left Joystick
			return -rTrigger + lTrigger + lStick;
			
		}
		//Sets the speed for both sides using XBController methods
		public void setSpeeds(double lStick, double rTrigger, double lTrigger){
			
			Drivetrain.frontRight.set(ControlMode.PercentOutput, (double)XBControllerR(lStick, rTrigger, lTrigger));
			Drivetrain.frontLeft.set(ControlMode.PercentOutput, (double)XBControllerR(lStick, rTrigger, lTrigger));
			
			
		}
	
	protected void initialize() {
		Drivetrain.setFollowing();
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		setSpeeds(getStickHorizontal('l'), getTriggerValue('r'), getTriggerValue('l'));
		//SmartDashboard.putNumber("Right Sensor Position", Drivetrain.FREncoder.getDistance());
		//SmartDashboard.putNumber("Right Sensor Velocity", Drivetrain.FREncoder.getRate());
	}
	protected boolean isFinished() {
		
		return true; // maybe true?
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
