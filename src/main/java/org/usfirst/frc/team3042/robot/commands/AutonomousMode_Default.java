// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMode_Default extends CommandGroup {
  /** Add your docs here. */
  public AutonomousMode_Default() {
    addParallel(new LightRing_Strobe());
    addSequential(new Drivetrain_GyroStraight(50, 30));
    addSequential(new Drivetrain_GyroTurn(360));
    addSequential(new Spinner_SetSpeed());
  }
}
