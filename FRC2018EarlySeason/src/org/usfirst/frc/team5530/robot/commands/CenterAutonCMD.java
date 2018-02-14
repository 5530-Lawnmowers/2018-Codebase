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

public class CenterAutonCMD extends Command{
	
	public CenterAutonCMD() {
		super("CenterAutonCMD");
		requires(Robot.drivetrain);
	}
	protected void initialize() {
		Drivetrain.setFollowing();
		Drivetrain.frontLeft.setSelectedSensorPosition(0, 0, 0);
	}
	//Whenever this command is called, setspeeds is called
	protected void execute() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			//3585.44
			Drivetrain.frontLeft.config_kP(0, SmartDashboard.getNumber("P Value: ", 0), 2000);
			Drivetrain.frontLeft.config_kI(0, SmartDashboard.getNumber("I Value: ", 0), 2000);
			Drivetrain.frontLeft.config_kD(0, SmartDashboard.getNumber("D Value: ", 0), 2000);
			SmartDashboard.putNumber("Encoder Position: ", Drivetrain.frontLeft.getSelectedSensorPosition(0));	
			Drivetrain.frontLeft.set(ControlMode.Position, SmartDashboard.getNumber("Distance: ", 0));
		}
		else {	
			//Put right auton code here
			
		}
	}
	protected boolean isFinished() {
		
		return false; // maybe true?
	}
	protected void end() {
		
	}
	protected void interrupted() {
		
	}
	
}
