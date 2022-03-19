package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous_Mode extends SequentialCommandGroup {
  /** Creates a new Autonomous_Mode. */
  public Autonomous_Mode() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new Drivetrain_GyroStraight(50.0, 30.0));
  }
}
