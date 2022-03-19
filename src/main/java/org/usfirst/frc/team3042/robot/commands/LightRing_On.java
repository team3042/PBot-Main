package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.util.sendable.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LightRing;

/** LightRing_On **************************************************************/
public class LightRing_On extends CommandBase {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIGHT_RING;
	
	/** Instance Variables ****************************************************/
	LightRing lightRing = Robot.lightRing;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(lightRing));
	
	/** LightRing_On **********************************************************/
	public LightRing_On() {
		log.add("Constructor", Log.Level.TRACE);
		
		addRequirements(lightRing);
		lightRing.off();
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	public void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		lightRing.on();
	}
	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	public void execute() {
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