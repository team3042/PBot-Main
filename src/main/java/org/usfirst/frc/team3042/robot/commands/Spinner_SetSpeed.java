package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.util.sendable.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;

/** Spinner_SetSpeed **********************************************************/
public class Spinner_SetSpeed extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final double DEFAULT_SPEED = RobotMap.SPINNER_DEFAULT_SPEED;
	
	/** Instance Variables ****************************************************/
	Spinner spinner = Robot.spinner;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(spinner));
	String speedLabel = "Spinner Speed";
	
	/** Spinner_SetSpeed ******************************************************
	 * Required subsystems will cancel commands when this command is run. */
	public Spinner_SetSpeed() {
		log.add("Constructor", Log.Level.TRACE);
		requires(spinner);
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);

		spinner.closedLoop.setSpeed(-1 * DEFAULT_SPEED);
	}
	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {}
	
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
		spinner.stop();
	}
}