package org.usfirst.frc.team3042.robot.commands.helperCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/** Wait *******************************************************
 * Waits for a specified number of seconds. Useful for autonomous command groups! */
public class Wait extends CommandGroup {

	double duration;
	Timer timer = new Timer();

	public Wait(double time) {
		duration = time;
	}

	public void initialize() {
		timer.reset();
		timer.start();
	}
	
	public boolean isFinished() {
		return timer.get() >= duration;
	}

	public void end(boolean interrupted) {
		timer.reset();
	}
}