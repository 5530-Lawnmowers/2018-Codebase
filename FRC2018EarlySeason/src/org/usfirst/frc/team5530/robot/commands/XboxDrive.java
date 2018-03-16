package org.usfirst.frc.team5530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;

//This command uses input from the xbox controller to control the drive train. 


public class XboxDrive extends Command{
	
	public static double OutputOld;
	public static double allowableRate = 1/40;
	public static double currentSpeedL = 0;
	public static double currentSpeedR = 0;
	
	public XboxDrive() {
		super("XboxDriveCMD");
		requires(Robot.drivetrainSS);
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
	double GetPositionFiltered(double currentInput, double currentSpeed){
		if (Math.abs(currentSpeed - currentInput) > .025 && Math.abs(currentSpeed) < 0.3) {
			System.out.println("Rate Applied");
			if (currentSpeed < currentInput) return currentSpeed + .025;
			else return currentSpeed - .025;
		}
		else {
			System.out.println("Not Applied");
//				if (currentSpeed < currentInput) return currentSpeed + .15;
//				else return currentSpeed - .15;
			return currentInput;
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
			if (-rTrigger + lTrigger + lStick < 0) return -Math.pow((-rTrigger + lTrigger + lStick), 2);
			return Math.pow(-rTrigger + lTrigger + lStick, 2);
		}
		
		//Calculates left speed based on Controller output
		public double XBControllerL(double lStick, double rTrigger, double lTrigger){
			//speed of left side = amount Accelerator is pushed down minus
			//amount Deccelerator is pushed down + lateral input from left Joystick
			if (rTrigger - lTrigger + lStick < 0) return -Math.pow((rTrigger - lTrigger + lStick), 2);
			return Math.pow(rTrigger - lTrigger + lStick, 2);
		
		}
		//Sets the speed for both sides using XBController methods
		public void setSpeeds(double lStick, double rTrigger, double lTrigger){
			
//			DrivetrainSS.frontRight.set(ControlMode.PercentOutput, XBControllerR(lStick, rTrigger, lTrigger));
//			DrivetrainSS.frontLeft.set(ControlMode.PercentOutput, XBControllerL(lStick, rTrigger, lTrigger));
			
			DrivetrainSS.frontRight.set(ControlMode.PercentOutput, GetPositionFiltered((double)XBControllerR(lStick, rTrigger, lTrigger), currentSpeedR));
			DrivetrainSS.frontLeft.set(ControlMode.PercentOutput, GetPositionFiltered((double)XBControllerL(lStick, rTrigger, lTrigger), currentSpeedL));
			currentSpeedL = GetPositionFiltered((double)XBControllerL(lStick, rTrigger, lTrigger), currentSpeedL);
			currentSpeedR = GetPositionFiltered((double)XBControllerR(lStick, rTrigger, lTrigger), currentSpeedR);
		}
	
	protected void initialize() {
		DrivetrainSS.setFollowing();
	}
	
	protected void execute() {
		setSpeeds(getStickHorizontal('l'), getTriggerValue('r'), getTriggerValue('l'));
		
	}
	protected boolean isFinished() {
		
		return true;
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
