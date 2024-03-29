package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.util.sendable.SendableRegistry;

/** Drivetrain ****************************************************************
 * The drivetrain subsystem for the robot. */
public class Drivetrain extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final NeutralMode BRAKE_MODE = RobotMap.DRIVETRAIN_BRAKE_MODE;
	private static final boolean REVERSE_LEFT_MOTOR = RobotMap.REVERSE_LEFT_MOTOR;
	private static final boolean REVERSE_RIGHT_MOTOR = RobotMap.REVERSE_RIGHT_MOTOR;
	private static final double distance = RobotMap.DISTANCE;
	private static final int ping = RobotMap.DIO_ULTRASONIC_PING;
	private static final int echo = RobotMap.DIO_ULTRASONIC_ECHO;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	TalonSRX leftMotor = new TalonSRX(CAN_LEFT_MOTOR);
	TalonSRX rightMotor = new TalonSRX(CAN_RIGHT_MOTOR);
	DrivetrainEncoders encoders;
	Ultrasonic ultra = new Ultrasonic(ping, echo);
	
	/** Drivetrain ************************************************************
	 * Set up the talons for desired behavior. */
	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);
		
		encoders = new DrivetrainEncoders(leftMotor, rightMotor);
		Ultrasonic.setAutomaticMode(true);
		
		initMotor(leftMotor, REVERSE_LEFT_MOTOR);
		initMotor(rightMotor, REVERSE_RIGHT_MOTOR);
	}
	private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(new Drivetrain_TankDrive());
	}

	// Ultrasonic sensor methods
	public double getDistance() {
		return ultra.getRangeInches();
	}

	public boolean isSensing() {
		return ultra.getRangeInches() < distance;
	}
	
	/** Methods for setting the motors in Percent Vbus mode *******************/
	public void setPower(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);
				
		leftMotor.set(ControlMode.PercentOutput, leftPower);
		rightMotor.set(ControlMode.PercentOutput, rightPower);
	}
	public void stop() {
		setPower(0.0, 0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	/** Provide commands access to the encoders ****************/
	public DrivetrainEncoders getEncoders() {
		return encoders;
	}
}