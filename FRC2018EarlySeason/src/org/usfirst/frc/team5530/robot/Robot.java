
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

	Command autonomousCommand;
	Command initializeMotors;
	
	public boolean autonFlag = true;
	
	public G_LeftTurnAuton leftSwitch;
	public G_RightTurnAuton rightSwitch;
	public G_DriveForwardAndDeliver driveForwardAndDeliver;
	public G_DriveForward driveForward;
	public CenterToLeftSwitch CTLS;
	public G_StraightScaleAuton straightScaleAuton;
	public G_DriveForward drive170;
	public G_TestTurn testTurn;
	public G_SideSwitch sideSwitch;
	
	
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
	public static Command armMotorTest;
	public static Command servo0;
	public static Command servo1;
	

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
		
//		autonChooser = new SendableChooser<Command>();\
		SmartDashboard.putString("autonChooser", "DF");
//		autonChooser.addDefault("Drive Forward and Deliver", new G_DriveForwardAndDeliver());
//		autonChooser.addObject("Forward", new G_DriveForward());
//		autonChooser.addObject("CenterSwitch", object);
//		autonChooser.addObject("Scale", object);
//				
//		SmartDashboard.putNumber("Forward P Value", 0.15);
//		SmartDashboard.putNumber("Forward I Value", 0.000001);
//		SmartDashboard.putNumber("Forward D Value", 10);
//		
//		SmartDashboard.putNumber("ClimbSpeed", 0.3);
//		SmartDashboard.putNumber("ServoPos", .33);
//		SmartDashboard.putNumber("Inside Turn P Value", 0.072);
//		SmartDashboard.putNumber("Inside Turn I Value", 0);
//		SmartDashboard.putNumber("Inside Turn D Value", 0);
//		
//		SmartDashboard.putNumber("Outside Turn P Value", 0.228);
//		SmartDashboard.putNumber("Outside Turn I Value", 0);
//		SmartDashboard.putNumber("Outside Turn D Value", 0);
		SmartDashboard.putNumber("Input 1", 0);
		SmartDashboard.putNumber("Input 2", 0);
		SmartDashboard.putNumber("Input 3", 0);
		SmartDashboard.putNumber("Input 4", 0);
		SmartDashboard.putNumber("Input 5", 0);
		SmartDashboard.putString("Str Input 1", "");
		SmartDashboard.putString("Str Input 2", "");
		SmartDashboard.putString("Str Input 3", "");
		SmartDashboard.putBoolean("Manual Auton Control?", false);
		
//		SmartDashboard.putNumber("angle", 0);
//		SmartDashboard.putString("direction", "l");
//		SmartDashboard.putBoolean("Elevator Limit Switch", ElevatorSS.elevatorSwitchTop.get());
		
//		SmartDashboard.putNumber("Potentiometer Arm: ", ArmSS.potentiometer0.getValue());
//		SmartDashboard.putNumber("Elevator Encoder Value: ", ElevatorSS.Elevator0.getSelectedSensorPosition(0));
//		SmartDashboard.putNumber("Right Sensor Position", DrivetrainSS.frontRight.getSelectedSensorPosition(0));
//		SmartDashboard.putNumber("Left Sensor Position", DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Sensor Velocity", DrivetrainSS.frontRight.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left Sensor Velocity", DrivetrainSS.frontLeft.getSelectedSensorVelocity(0));
//		SmartDashboard.putNumber("Output Percent R", DrivetrainSS.frontRight.getMotorOutputPercent());
//		SmartDashboard.putNumber("Output Percent L", DrivetrainSS.frontLeft.getMotorOutputPercent());
//		SmartDashboard.clearPersistent("Right Sensor Position");
//		SmartDashboard.clearPersistent("Left Sensor Position");
//		SmartDashboard.clearPersistent("Right Sensor Velocity");
//		SmartDashboard.clearPersistent("Left Sensor Velocity");
//		SmartDashboard.clearPersistent("Output Percent R");
//		SmartDashboard.clearPersistent("Output Percent L");
//		SmartDashboard.putBoolean("Elevator Limit Switch", ElevatorSS.elevatorSwitchTop.get());
		
		

		
		initializeMotors = new InitializeMotors();
		
		
		
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
		elevatorTest = new ElevatorTest(time + 6);
		elevatorMotor0Test = new IndividualElevatorTest(ElevatorSS.Elevator0);
		elevatorMotor1Test = new IndividualElevatorTest(ElevatorSS.Elevator1);
		
		armMotorTest = new PotentiometerActuationTest(time);
		servo0 = new ServoActuationTest(ClimbSS.servo0, time);
		servo1 = new ServoActuationTest(ClimbSS.servo1, time);
		
		driveForward = new G_DriveForward(100);
		driveForwardAndDeliver = new G_DriveForwardAndDeliver();
		leftSwitch = new G_LeftTurnAuton();
		rightSwitch = new G_RightTurnAuton();
		CTLS = new CenterToLeftSwitch();
		drive170 = new G_DriveForward(170);
		testTurn = new G_TestTurn();
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
//		autonomousCommand = (Command) autonChooser.getSelected();
//		autonomousCommand.start();
		
		autonFlag = true;
//		CTLS.MPR.reset();
//		CTLS.MPL.reset();
		
		DrivetrainSS.frontLeft.selectProfileSlot(0, 0); 
		DrivetrainSS.frontRight.selectProfileSlot(0, 0);
		DrivetrainSS.frontRight.config_kP(3, .25, 0);
		DrivetrainSS.frontLeft.config_kP(3, .25, 0);
		DrivetrainSS.frontRight.config_kI(3, 0, 0);
		DrivetrainSS.frontLeft.config_kI(3, 0, 0);
		DrivetrainSS.frontRight.config_kD(3, 0, 0);
		DrivetrainSS.frontLeft.config_kD(3, 0, 0);
		
		DrivetrainSS.frontLeft.selectProfileSlot(1, 0); 
		DrivetrainSS.frontRight.selectProfileSlot(1, 0);
		DrivetrainSS.frontLeft.config_kP(1, .45, 0);
		DrivetrainSS.frontLeft.config_kI(1, 0, 0);
		DrivetrainSS.frontLeft.config_kD(1, 0, 0);
		DrivetrainSS.frontRight.config_kP(1, .45, 0);
		DrivetrainSS.frontRight.config_kI(1, 0, 0);
		DrivetrainSS.frontRight.config_kD(1, 0, 0);
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
		SmartDashboard.updateValues();
		
		System.out.println("Left Encoder Value: " + DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
		System.out.println("Right Encoder Value: " + DrivetrainSS.frontRight.getSelectedSensorPosition(0) + "\n");
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0 && autonFlag) {
			if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("CenterSwitch")) {
				if (gameData.charAt(0) == 'L') {
					leftSwitch.start();
					autonFlag = false;
				}
				else if (gameData.charAt(0) == 'R') {
					rightSwitch.start();
					autonFlag = false;	
				}
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("DFDL")) {
				if (gameData.charAt(0) == 'L') {
					driveForwardAndDeliver.start();
					autonFlag = false;
				}
				else if (gameData.charAt(0) == 'R') {
					driveForward.start();
					autonFlag = false;
				}
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("DFDR")) {
				if (gameData.charAt(0) == 'L') {
					driveForward.start();
					autonFlag = false;
				}
				else if (gameData.charAt(0) == 'R') {
					driveForwardAndDeliver.start();
					autonFlag = false;
				}
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("DF")) {
				driveForward.start();
				autonFlag = false;
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("MM")) {
				leftSwitch.start();
				autonFlag = false;
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("MP")) {
				CTLS.start();
				autonFlag = false;
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("SSR")) {
				if (gameData.charAt(0) == 'R') {
					sideSwitch = new G_SideSwitch("L");
					sideSwitch.start();
					autonFlag = false;
				} else if (gameData.charAt(1) == 'R') {
					straightScaleAuton = new G_StraightScaleAuton(Character.toString(gameData.charAt(1)));
					straightScaleAuton.start();
//					drive170.start();	
					autonFlag = false;
				} else {
					drive170.start();	
					autonFlag = false;
				}
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("SSL")) {
				if (gameData.charAt(0) == 'L') {
					sideSwitch = new G_SideSwitch("R");
					sideSwitch.start();
					autonFlag = false;
				} else if (gameData.charAt(1) == 'L') {
					straightScaleAuton = new G_StraightScaleAuton(Character.toString(gameData.charAt(1)));
					straightScaleAuton.start();
//					drive170.start();
					autonFlag = false;
				} else {
					drive170.start();
					autonFlag = false;
				}
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("DTS")) {
				drive170.start();
				autonFlag = false;
			} else if (SmartDashboard.getString("autonChooser", "DF").equalsIgnoreCase("TT")) {
				testTurn.start();
				autonFlag = false;	
			} 
			
		}
		
	}
	

	@Override
	public void teleopInit() {
		initializeMotors.start();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.	
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Elevator Limit Switch", ElevatorSS.elevatorSwitchTop.get());
		SmartDashboard.putBoolean("Intake On", IntakeSS.intakeOn);	
		
		System.out.println("Left Encoder Value: " + DrivetrainSS.frontLeft.getSelectedSensorPosition(0));
		System.out.println("Right Encoder Value: " + DrivetrainSS.frontRight.getSelectedSensorPosition(0) + "\n");
		
		//xboxdrive.setSpeeds(xboxdrive.getStickHorizontal('l'), xboxdrive.getTriggerValue('r'), xboxdrive.getTriggerValue('l'));

		
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
