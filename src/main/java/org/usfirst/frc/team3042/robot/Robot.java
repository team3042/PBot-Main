package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.AutonomousMode_Default;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3042.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3042.robot.subsystems.LightRing;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. */
public class Robot extends TimedRobot { 
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ROBOT;
	
	/** Create Subsystems *****************************************************/
	private Log log = new Log(LOG_LEVEL, "Robot");
	public static final Drivetrain 	drivetrain 	= new Drivetrain();
	public static final Spinner 	spinner 	= new Spinner();
	public static final Gyroscope 	gyroscope 	= new Gyroscope();
	public static final LightRing 	lightRing 	= new LightRing();
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	UsbCamera camera1;
	// UsbCamera camera2;

	/** robotInit *************************************************************
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code. */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);

		/*Code For Cameras (Microsoft Lifecams)*/

		camera1 = CameraServer.startAutomaticCapture(0);
		// camera2 = CameraServer.startAutomaticCapture(1);

		camera1.setResolution(640, 480);
		camera1.setFPS(24);
		// camera2.setResolution(1280, 720);
		// camera2.setFPS(15);

		oi = new OI();
		chooser.setDefaultOption("Default Auto", new AutonomousMode_Default());
		chooser.addOption("My Auto", new ExampleCommand());
		SmartDashboard.putData("Auto Mode", chooser);
	}

	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled. */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
	}

	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode. */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/** autonomousInit ********************************************************
	 * Run once at the start of autonomous mode. */
	public void autonomousInit() {
		log.add("Autonomous Init", Log.Level.TRACE);
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("STROBE", lightRing.getStrobeStatus());
	}
	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode. */
	public void teleopInit() {
		log.add("Teleop Init", Log.Level.TRACE);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("STROBE", lightRing.getStrobeStatus());
	}
}