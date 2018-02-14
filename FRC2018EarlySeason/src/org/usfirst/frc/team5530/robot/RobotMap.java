package org.usfirst.frc.team5530.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	//Driving
	public static int FR = 1; //1
	public static int FL = 2; //2
	public static int BR = 3;
	public static int BL = 4; 
	
	//Arm
	public static int L0 = 10; //10
	
	//Elevator
	public static int L1 = 11; //11 
	public static int L2 = 12; //12
	
	//Intake
	public static int I0 = 20;
	public static int I1 = 21;
	
	//Climb
	public static int C0 = 30; //30
	public static int C1 = 31; //31
	
	//Limit Switches - Digital Input
	public static int LS0 = 0;
	public static int LS1 = 1;
	public static int LS2 = 2;
	public static int LS3 = 3;
	
	
	//Servos - PWM
	public static int S0 = 0; 
	public static int S1 = 1;
	
	//Potentiometer - Analog Input
	public static int P0 = 0;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
