package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTrain_Path extends CommandGroup {
  /** Add your docs here. */
  public DriveTrain_Path() {
    addSequential(new Drivetrain_GyroTurn(180));
    addParallel(new LightRing_Strobe(0.04), 2.0);
    addSequential(new Drivetrain_GyroTurn(180));
    addParallel(new LightRing_Strobe(0.04), 2.0);
    addParallel(new LightRing_Strobe(), 2.0);
    addSequential(new Spinner_SetSpeed());
  }
}
