 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;

/** Spinner_AdjustSpeed *******************************************************/
public class Spinner_AdjustSpeed extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Spinner spinner = Robot.spinner;
	private boolean increase;
	
	/** Spinner_AdjustSpeed ***************************************************/
	public Spinner_AdjustSpeed(boolean increase) {
		log.add("Constructor", Log.Level.TRACE);
		requires(spinner);
		this.increase = increase;
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		spinner.adjustSpeed(increase); //adjust speed value

		if (spinner.closedLoop.spinning == true) {
			spinner.closedLoop.setSpeed(spinner.speed); //set spinner speed to new value 
		}
	}
	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		terminate();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		terminate();
	}
	
	/** Graceful End **********************************************************/
	private void terminate() {
		spinner.stop();
	}
}