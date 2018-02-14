
package org.usfirst.frc.team5530.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.networktables.*;

//import all subsystems, no need to import anything else
import org.usfirst.frc.team5530.robot.subsystems.*;
import org.usfirst.frc.team5530.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeR
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	//Declareeach of the subsystems
	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Arm arm;
	public static Intake intake;
	public static Climb climb;
	public static OI oi;	
	Command autonomousCommand;
	Command initializeMotors;
	Servo Servo0;
	Servo Servo1;
	SendableChooser autonChooser;
	int pValue;
	int iValue;
	int dValue;
	
	//Test Stuff
	double time;
	public static Command FRDriveTrainMotor;
	public static Command FLDriveTrainMotor;
	public static Command BRDriveTrainMotor;
	public static Command BLDriveTrainMotor;
	public static Command intakeMotor0;
	public static Command intakeMotor1;
	public static Command climbMotor0;
	public static Command climbMotor1;
	public static Command elevatorMotor0;
	public static Command elevatorMotor1;	
//	Command servo0;
//	Command servo1;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//create subystems	
		drivetrain = new Drivetrain();
		elevator = new Elevator();
		arm = new Arm();
		intake = new Intake();
		climb = new Climb();
		oi = new OI();
		initializeMotors = new InitializeMotorsCMD();
		Servo0 = new Servo(0);
		Servo1 = new Servo(1); 
		autonChooser = new SendableChooser();
		autonChooser.addDefault("Center", new CenterAutonCMD());
		//autonChooser.addObject("Left", new LeftAuton());
		//autonChooser.addObject("Right", new RightAuton());
		SmartDashboard.putData("Autonomous Mode Chooser", autonChooser);
		SmartDashboard.putNumber("P Value: ", 0.02);
		SmartDashboard.putNumber("I Value: ", 0.000000875);
		SmartDashboard.putNumber("D Value: ", 3.0);
		SmartDashboard.putNumber("Distance: ", 40960);
		
		//Test Stuff
		time = 1;
		FRDriveTrainMotor = new NonLimitedTestCMD(Drivetrain.frontRight, time);
		FLDriveTrainMotor = new NonLimitedTestCMD(Drivetrain.frontLeft, time);
		BRDriveTrainMotor = new NonLimitedTestCMD(Drivetrain.backRight, time);
		BLDriveTrainMotor = new NonLimitedTestCMD(Drivetrain.backLeft, time);
		intakeMotor0 = new NonLimitedTestCMD(Intake.Intake0, time);
		intakeMotor1 = new NonLimitedTestCMD(Intake.Intake1, time);
		climbMotor0 = new NonLimitedTestCMD(Climb.Climb0, time);
		climbMotor1 = new NonLimitedTestCMD(Climb.Climb1, time);
		elevatorMotor0 = new ElevatorTestCMD(Elevator.Elevator0, time, Elevator.elevatorSwitchTop, Elevator.elevatorSwitchBot);
		elevatorMotor1 = new ElevatorTestCMD(Elevator.Elevator1, time, Elevator.elevatorSwitchTop, Elevator.elevatorSwitchBot);
//		servo0 = new ServoActuationTestCMD(Servo0, time);
//		servo1 = new ServoActuationTestCMD(Servo1, time);
		
		
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
		initializeMotors.start();
		autonomousCommand = (Command) autonChooser.getSelected();
		autonomousCommand.start();
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
		
//		//Limit Switches - ALL OF THESE LIMIT SWITCHES HAVE A DEFAULT VALUE OF TRUE
//		SmartDashboard.putBoolean("Intake 0 Limit Switch", Intake.intakeSwitch0.get()); 
//		SmartDashboard.putBoolean("Intake 1 Limit Switch", Intake.intakeSwitch1.get());
//		SmartDashboard.putBoolean("Scale 0 Limit Switch", Lift.liftSwitch0.get());
//		SmartDashboard.putBoolean("Scale 1 Limit Switch", Lift.liftSwitch1.get());
//		
//		//Servos
//		SmartDashboard.putNumber("Servo 0 Value", Servo0.getPosition());
//		SmartDashboard.putNumber("Servo 1 Value", Servo1.getPosition());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}
