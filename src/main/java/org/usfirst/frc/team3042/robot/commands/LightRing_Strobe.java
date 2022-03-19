package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.util.sendable.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LightRing;

/** LightRing_Strobe **************************************************************/
public class LightRing_Strobe extends CommandBase {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIGHT_RING;
	private static final double STROBE_INTERVAL = RobotMap.STROBE_INTERVAL;
	
	/** Instance Variables ****************************************************/
	LightRing lightRing = Robot.lightRing;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(lightRing));
	Timer timer = new Timer();
	double currentTime;
	
	/** LightRing_Strobe **********************************************************/
	public LightRing_Strobe() {
		log.add("Constructor", Log.Level.TRACE);
		
		addRequirements(lightRing);
		lightRing.off();
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	public void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		currentTime = 0.0;
		timer.start();
	}
	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	public void execute() {
		if (timer.get() >= STROBE_INTERVAL + currentTime)
		{
			lightRing.toggle();
			currentTime = timer.get();
		}
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute() */
	public boolean isFinished() {
		return false;
	}

	/** end *******************************************************************
	 * Called once after isFinished returns true */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		
		terminate();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		
		terminate();
	}
	
	/** Graceful End **********************************************************/
	private void terminate() {
		lightRing.off();
	}
}