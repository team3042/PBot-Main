package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.util.sendable.SendableRegistry;

/** ExampleSubsystem **********************************************************
 * An example subsystem template */
public class ExampleSubsystem extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_EXAMPLE_SUBSYSTEM;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	
	/** ExampleSubsystem *******************************************************/
	public ExampleSubsystem() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}