package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.util.sendable.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LightRing;

/** LightRing_Strobe **************************************************************/
public class LightRing_Strobe extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIGHT_RING;
	private static final double STROBE_INTERVAL = RobotMap.STROBE_INTERVAL;
	
	/** Instance Variables ****************************************************/
	LightRing lightRing = Robot.lightRing;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(lightRing));
	Timer timer = new Timer();
	double currentTime;
	double interval;
	
	/** LightRing_On **********************************************************/
	public LightRing_Strobe() {
		log.add("Constructor", Log.Level.TRACE);

		interval = STROBE_INTERVAL;
		
		requires(lightRing);
		lightRing.off();
	}

	public LightRing_Strobe(double strobeInterval) {
		log.add("Constructor", Log.Level.TRACE);

		interval = strobeInterval;
		
		requires(lightRing);
		lightRing.off();
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		currentTime = 0.0;
		timer.start();
	}
	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {
		if(timer.get() >= interval + currentTime)
		{
			lightRing.toggle();
			currentTime = timer.get();
		}
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute() */
	protected boolean isFinished() {
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