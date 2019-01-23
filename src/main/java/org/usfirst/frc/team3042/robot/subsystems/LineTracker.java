/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2Line;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.Pixy2Line.Vector;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LineTracker extends Subsystem {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_LINE_TRACKER;
  private static final int PIXY_PORT = RobotMap.LINE_TRACKER_PIXY_PORT;

  /** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
  Pixy2 pixy;
  
  public LineTracker() {
      log.add("Constructor", LOG_LEVEL);

      this.pixy = Pixy2.createInstance(LinkType.SPI);
      pixy.init(PIXY_PORT);
      
      // change to the line_tracking program.  Note, changeProg can use partial strings, so for example,
      // you can change to the line_tracking program by calling changeProg("line") instead of the whole
      // string changeProg("line_tracking")
      //int result = this.pixy.changeProg("line".toCharArray());
      //if (result == Pixy2.PIXY_RESULT_ERROR) {
      //  SmartDashboard.putString("linePrinter", "ERROR returned by pixy.changeProg");
      //}

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /** Command Controls ******************************************************/

  public void printLines() {
      byte modeRes = this.pixy.getLine().setMode(Pixy2Line.LINE_MODE_WHITE_LINE);
      byte res = this.pixy.getLine().getAllFeatures();
      System.out.println("getAllFeatures returned " + Integer.toHexString(res));

      Vector[] vectors = this.pixy.getLine().getVectors();
      if (vectors != null) {

        System.out.println("Vectors Found: " + vectors.length);
        for (Vector vector : vectors) {
          if (vector != null) {
            System.out.println(vector.toString());
          }
        }
        
      } else {
        System.out.println("***** vectors is null *****");
      }
  }

  public void printBlocks() {
    pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
    ArrayList<Block> blocks = pixy.getCCC().getBlocks();
    System.out.println("Blocks Found: " + blocks.size());
    for (Block b : blocks) {
        System.out.println(b.toString());
    }  
  }

  public void printVersion() {
    int res = pixy.getVersion();
    if (res != Pixy2.PIXY_RESULT_ERROR) {
      pixy.getVersionInfo().print();
    } else {
      System.out.println("***** ERROR Getting Pixy Version");
    }
  }

  public void followLine() {

    // Follows the code from https://github.com/charmedlabs/pixy2/blob/master/src/host/arduino/libraries/Pixy2/examples/line_zumo_demo/line_zumo_demo.ino
    // Will need to put put our own looping control in here since the Arduino example code
    // loops already.  The key will be determining when to stop

    //this will most likely not be a method, but rather it's own command that has
    //its own IsFinished() and we will most likely use 
    //the gyro turn to degree command (Drivetrain_GyroTurn) which takes angle in deg as a param
    //possibly command group? TDB. 
  }
}
