package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.DriveTrain_Path;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroTurn;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_Scale_Toggle;
import org.usfirst.frc.team3042.robot.commands.LightRing_On;
import org.usfirst.frc.team3042.robot.commands.LightRing_Strobe;
import org.usfirst.frc.team3042.robot.commands.Spinner_SetSpeed;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;

/** OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot. */
public class OI {	
	/** Configuration Constants ***********************************************/
	private static final int USB_GAMEPAD = RobotMap.USB_GAMEPAD;
	private static final int USB_JOY_LEFT = RobotMap.USB_JOYSTICK_LEFT;
	private static final int USB_JOY_RIGHT = RobotMap.USB_JOYSTICK_RIGHT;
	private static final boolean USE_JOYSTICKS = RobotMap.USE_JOYSTICKS;
	private static final double JOYSTICK_DRIVE_SCALE = RobotMap.JOYSTICK_DRIVE_SCALE;
	private static final double JOYSTICK_DEAD_ZONE = RobotMap.JOYSTICK_DEAD_ZONE;
	private static final double TRIGGER_SPINNER_SCALE = RobotMap.TRIGGER_SPINNER_SCALE;
	private static final int GAMEPAD_LEFT_Y_AXIS = Gamepad.LEFT_JOY_Y_AXIS;
	private static final int GAMEPAD_RIGHT_Y_AXIS = Gamepad.RIGHT_JOY_Y_AXIS;
	private static final int JOYSTICK_Y_AXIS = Gamepad.JOY_Y_AXIS;
	private static final int GAMEPAD_LEFT_TRIGGER = Gamepad.LEFT_TRIGGER;
	private static final int GAMEPAD_RIGHT_TRIGGER = Gamepad.RIGHT_TRIGGER;
	private static final double JOYSTICK_DRIVE_SCALE_LOW = RobotMap.JOYSTICK_DRIVE_SCALE_LOW;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(RobotMap.LOG_OI, "OI");
	Gamepad gamepad, joyLeft, joyRight;
	int driveAxisLeft, driveAxisRight;
	public static double CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
	public static boolean isLowScale = false;
	Spinner spinner = Robot.spinner;

	/** OI ********************************************************************
	 * Assign commands to the buttons and triggers
	 * 
	 * Example Commands:
	 * gamepad.A.whenPressed(new ExampleCommand());
	 * gamepad.B.toggleWhenPressed(new ExampleCommand());
	 * gamepad.X.whileHeld(new ExampleCommand());
	 * gamepad.Y.whenReleased(new ExampleCommand());
	 * gamepad.LT.toggleWhenActive(new ExampleCommand());
	 * gamepad.RT.whenActive(new ExampleCommand());
	 * gamepad.POVUp.whileActive(new ExampleCommand()); */
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);
		
		gamepad = new Gamepad(USB_GAMEPAD);
		
		/** Setup Driving Controls ********************************************/
		if (USE_JOYSTICKS) {
			joyLeft = new Gamepad(USB_JOY_LEFT);
			joyRight = new Gamepad(USB_JOY_RIGHT);
			driveAxisLeft = JOYSTICK_Y_AXIS;
			driveAxisRight = JOYSTICK_Y_AXIS;
		}
		else {
			joyLeft = gamepad;
			joyRight = gamepad;
			driveAxisLeft = GAMEPAD_LEFT_Y_AXIS;
			driveAxisRight = GAMEPAD_RIGHT_Y_AXIS;
		}
		
		/** PBOT Controls *****************************************************/
		gamepad.A.toggleWhenPressed(new LightRing_On());
		gamepad.B.toggleWhenPressed(new LightRing_Strobe());
		gamepad.X.toggleWhenPressed(new Drivetrain_GyroStraight(16.0, 100.0)); // distance, speed
		gamepad.Y.whenPressed(new Drivetrain_GyroTurn(90)); // degrees

		gamepad.RB.whenPressed(new Drivetrain_Scale_Toggle());
		gamepad.RB.whenReleased(new Drivetrain_Scale_Toggle());
		
		gamepad.LB.toggleWhenPressed(new Spinner_SetSpeed());
		gamepad.LT.whenActive(new Spinner_SetSpeed());
		gamepad.LT.whenActive(new LightRing_Strobe());
		gamepad.LT.whenInactive(new LightRing_Strobe());

		gamepad.Start.whenPressed(new DriveTrain_Path());
		gamepad.Back.toggleWhenPressed(new Drivetrain_GyroTurn(3600));
	}
	
	/** Access to the driving axes values *************************************
	 * A negative has been added to make pushing forward positive. */
	public double getDriveLeft() {
		double joystickValue = joyLeft.getRawAxis(driveAxisLeft);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	public double getDriveRight() {
		double joystickValue = joyRight.getRawAxis(driveAxisRight);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue *= CURRENT_DRIVE_SCALE;
		joystickValue *= -1.0;
		return joystickValue;
	}
	public void setNormalScale() {
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
    	isLowScale = false;
    }
    public void setLowScale() {
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE_LOW;
    	isLowScale = true;
    }
    public void toggleScale(){
    	if (isLowScale) {
    		setNormalScale();
    	}
    	else {
    		setLowScale();
		}
	}
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < JOYSTICK_DEAD_ZONE) joystickValue = 0.0;
		return joystickValue;
	}
	
	/** Access the POV value **************************************************/
	public int getPOV() {
		return gamepad.getPOV();
	}
	
	/** Access the Trigger Values *********************************************/
	public double getTriggerDifference() {
		double leftTrigger = gamepad.getRawAxis(GAMEPAD_LEFT_TRIGGER);
		double rightTrigger = gamepad.getRawAxis(GAMEPAD_RIGHT_TRIGGER);
		return (rightTrigger - leftTrigger) * TRIGGER_SPINNER_SCALE;
	}
}