package org.usfirst.frc.team5530.robot.commands.TestCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogInput;


import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.*;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PotentiometerActuationTest extends Command{
	
	double time;
	double counter = 0;
	boolean finished = false;
	boolean isGoingUp = true;
	public static final double top = 450;
	public static final double bot = 2600;
	
	
	public PotentiometerActuationTest(double time) {
		this.time = time*50;
		requires(Robot.armSS);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		if (ArmSS.potentiometer0.getValue() >= top && isGoingUp && counter <= time){ 
			ArmSS.arm.set(1);
		}else if (isGoingUp) {
			ArmSS.arm.set(-1);
			isGoingUp = false;
		}else if (ArmSS.potentiometer0.getValue() <= bot && counter < time * 2) {
			ArmSS.arm.set(1);
		}else finished = true;
		counter ++;
	}	
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
		ArmSS.arm.set(0);
		System.out.println("End");
	}
	protected void interrupted() {
		ArmSS.arm.set(0);
	}
	
	
	
}

