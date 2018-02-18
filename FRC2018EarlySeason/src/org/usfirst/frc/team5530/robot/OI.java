package org.usfirst.frc.team5530.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team5530.robot.commands.*;
import org.usfirst.frc.team5530.robot.subsystems.DrivetrainSS;
import org.usfirst.frc.team5530.robot.commands.TestCommands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	public static Joystick stick1 = new Joystick(0);
	public static Joystick stick2 = new Joystick(2);
	public static XboxController XBController = new XboxController(1);
	public static Button[] buttons = new Button[12];
	public static Button testButton;
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	public OI(){
		//Creates an array of button[x] is button x+1 on the joystick

		for(int i=1; i <= 12; i++) {
			buttons[i-1] = new JoystickButton(stick1, i);
		}
		Button xboxButtonA = new JoystickButton(XBController, 1),
				xboxButtonB = new JoystickButton(XBController, 2),
				xboxButtonX = new JoystickButton(XBController, 3),
				xboxButtonY = new JoystickButton(XBController, 4),
				xboxButtonLB = new JoystickButton(XBController, 5),
				xboxButtonRB = new JoystickButton(XBController, 6);

		buttons[0].whileHeld(new ManualArm()); //Button 1
		buttons[3].whileHeld(new Climb()); //Button 4
		buttons[6].toggleWhenPressed(new LiftBot()); //Button 7
		buttons[7].toggleWhenPressed(new LiftTop()); //Button 8
		buttons[9].toggleWhenPressed(new ArmTop()); //Button 10
		
		xboxButtonLB.whenPressed(new Deliver());
		xboxButtonRB.toggleWhenPressed(new Intake()); 
		
		buttons[5].whenPressed(new NonLimitedTest(DrivetrainSS.frontRight, 1)); //Button 6
		
//		Button[][] stickbutton = new Button[2][12]
//		
//		for(int stick=0; stick<=1; stick++){
//			for(int button=1; button<=12; button++) {
//				stickbutton[stick][button-1]= new JoystickButton(sticks[stick],button);
//			}
//		}
//		
//		stickbutton[0][0].whenPressed(new DriveDistance());
//		
	}
	
	public static boolean getButtonValue(int i) {
		return buttons[i].get();
	}
	
	
	
	/*Button[][] stickbutton = new Button[2][12];
	
		private int stick=0;
		private int button=1;
		//why is there { before the while?
		{while (stick<=1){
			while (button<=12){
				stickbutton[stick][button-1]= new JoystickButton(sticks[stick],button);
				button++;
			}
			stick++;
		}}*/
	
	


	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());
	


	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	//{stickbutton[0][0].whenPressed(new DriveDistance());}
}
