package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Path extends CommandGroup {
  /** Add your docs here. */
  public Path() {
    addParallel(new LightRing_Strobe());
    addSequential(new Drivetrain_GyroStraight(50, 30));
    addSequential(new Drivetrain_GyroTurn(360));
    addSequential(new Spinner_SetSpeed());
  }
}
