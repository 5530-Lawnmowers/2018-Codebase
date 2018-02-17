
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
import org.usfirst.frc.team5530.robot.commands.TestCommands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeR
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	//Declareeach of the subsystems
	public static DrivetrainSS drivetrainSS;
	public static ElevatorSS elevatorSS;
	public static ArmSS armSS;
	public static IntakeSS intakeSS;
	public static ClimbSS climbSS;
	public static OI oi;
//	Servo Servo0;
//	Servo Servo1;

	SendableChooser<Command> autonChooser;
	
	Command autonomousCommand;
	Command initializeMotors;
	
	//Test Stuff
	double time;
	public static Command FRDriveTrainMotorTest;
	public static Command FLDriveTrainMotorTest;
	public static Command BRDriveTrainMotorTest;
	public static Command BLDriveTrainMotorTest;
	public static Command intakeMotor0Test;
	public static Command intakeMotor1Test;
	public static Command climbMotor0Test;
	public static Command climbMotor1Test;
	public static Command elevatorTest;
	public static Command elevatorMotor0Test;
	public static Command elevatorMotor1Test;	
	public static Command armMotor0Test;
//	Command servo0;
//	Command servo1;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//create subystems	
		drivetrainSS = new DrivetrainSS();
		elevatorSS = new ElevatorSS();
		armSS = new ArmSS();
		intakeSS = new IntakeSS();
		climbSS = new ClimbSS();
		
		oi = new OI();
		initializeMotors = new InitializeMotors();
//		Servo0 = new Servo(0);
//		Servo1 = new Servo(1); 
		autonChooser = new SendableChooser<Command>();
		autonChooser.addDefault("Drive Forward and Deliver", new DriveForwardAndDeliver());
		autonChooser.addObject("Turn", new DriveForwardAndTurn());
		//autonChooser.addObject("Left", new LeftAuton());
		//autonChooser.addObject("Right", new RightAuton());
		SmartDashboard.putData("Autonomous Mode Chooser", autonChooser);
		SmartDashboard.putNumber("P Value", 0.1);
		SmartDashboard.putNumber("I Value", 0.000001);
		SmartDashboard.putNumber("D Value", 15);
		SmartDashboard.putNumber("Distance", 100);
		SmartDashboard.putNumber("Turn Angle", 0);
		
		//Test Stuff
		time = 1;
		FRDriveTrainMotorTest = new NonLimitedTest(DrivetrainSS.frontRight, time);
		FLDriveTrainMotorTest = new NonLimitedTest(DrivetrainSS.frontLeft, time);
		BRDriveTrainMotorTest = new NonLimitedTest(DrivetrainSS.backRight, time);
		BLDriveTrainMotorTest = new NonLimitedTest(DrivetrainSS.backLeft, time);
		intakeMotor0Test = new NonLimitedTest(IntakeSS.Intake0, time);
		intakeMotor1Test = new NonLimitedTest(IntakeSS.Intake1, time);
		climbMotor0Test = new NonLimitedTest(ClimbSS.Climb0, time);
		climbMotor1Test = new NonLimitedTest(ClimbSS.Climb1, time);
		elevatorTest = new ElevatorTest(time + 5);
		elevatorMotor0Test = new IndividualElevatorTest(ElevatorSS.Elevator0);
		elevatorMotor1Test = new IndividualElevatorTest(ElevatorSS.Elevator1);
		
		armMotor0Test = new PotentiometerActuationTest(ArmSS.arm, ArmSS.potentiometer0, time);
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
		initializeMotors.start();
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
		autonomousCommand = (Command) autonChooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Right Encoder Position", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Encoder Position", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
	}
	

	@Override
	public void teleopInit() {
		initializeMotors.start();
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
		SmartDashboard.putNumber("Potentiometer Arm: ", ArmSS.potentiometer0.getValue());
		SmartDashboard.putNumber("Elevator Encoder Value: ", ElevatorSS.Elevator0.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Sensor Position", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Sensor Position", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
	}
	
	
	@Override
	public void testInit() {
		//Encoder
		SmartDashboard.putNumber("FR Encoder Position", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
		Scheduler.getInstance().add(new NonLimitedTest(DrivetrainSS.frontRight, time));
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
		Scheduler.getInstance().run();
	}
}
