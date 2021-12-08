package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Spinner *******************************************************************/
public class Spinner extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER;
	private static final int CAN_SPINNER = RobotMap.CAN_SPINNER;
	private static final NeutralMode BRAKE_MODE = RobotMap.SPINNER_BRAKE_MODE;
	private static final boolean REVERSE_SPINNER = RobotMap.REVERSE_SPINNER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL,SendableRegistry.getName(this));
	public TalonSRX motor = new TalonSRX(CAN_SPINNER);
	SpinnerEncoder encoder;
	public SpinnerClosedLoop closedLoop;
	
	/** Spinner ***************************************************************/
	public Spinner() {
		log.add("Constructor", LOG_LEVEL);
		
		encoder = new SpinnerEncoder(motor);
 		closedLoop = new SpinnerClosedLoop(motor, encoder);
		
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(REVERSE_SPINNER); 	// affects percent Vbus mode
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
	/** Command Control *******************************************************/
	public void setPower(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);

		motor.set(ControlMode.PercentOutput, power);
	}
	public void stop() {
		setPower(0.0);
	}

	/** Provide commands access to the encoder ********************************/
	public SpinnerEncoder getEncoder() {
		return encoder;
	}
}