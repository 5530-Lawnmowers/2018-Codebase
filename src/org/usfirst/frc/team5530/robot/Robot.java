
package org.usfirst.frc.team5530.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;



//import all subsystems, no need to import anything else
import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	//Declareeach of the subsystems
	public static Drivetrain drivetrain;
	public static Lift lift;
	public static Intake intake;
	public static Climb climb;
	public static OI oi;	
	Command autonomousCommand;
	DigitalInput limitSwitch0;
	DigitalInput limitSwitch1;
	//AnalogInput Servo0; I don't know why, but this is much harder to use, just use the more specific class Servo from wpilib
	//AnalogInput Servo1;
	Servo Servo0;
	Servo Servo1;
		
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//create subystems	
		drivetrain = new Drivetrain();
		lift = new Lift();
		intake = new Intake();
		climb = new Climb();
		oi = new OI();
		autonomousCommand = new DriveForwardTalonBased();
		limitSwitch0 = new DigitalInput(0);
		limitSwitch1 = new DigitalInput(1);
		//Servo0 = new AnalogInput(0);
		//Servo1 = new AnalogInput(1);
		Servo0 = new Servo(0);
		Servo1 = new Servo(1);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//xboxdrive.setSpeeds(xboxdrive.getStickHorizontal('l'), xboxdrive.getTriggerValue('r'), xboxdrive.getTriggerValue('l'));
		
	}
	
	
	@Override
	public void testInit() {
		//Motors
		SmartDashboard.putBoolean("C0", false);
		SmartDashboard.putBoolean("C1", false);
		boolean[] components = new boolean[] {false, false, false, false, false, false};	
		SmartDashboard.putBooleanArray("Components", components);
		
		//Encoder
		SmartDashboard.putNumber("FR Encoder Position", Drivetrain.frontRight.getSelectedSensorPosition(0));
		
		//Limit Switches
		SmartDashboard.putBoolean("Limit Switch", limitSwitch0.get());
		SmartDashboard.putBoolean("Limit Switch", limitSwitch1.get());
		
		//Servos
		SmartDashboard.putNumber("Servo 0 Value", Servo0.get());
		SmartDashboard.putNumber("Servo 1 Value", Servo1.get());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}
