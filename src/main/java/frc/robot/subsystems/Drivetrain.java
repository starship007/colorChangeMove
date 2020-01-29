/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Talon;




public class Drivetrain extends SubsystemBase {
  static Talon leftMotor = null;
  static Talon rightMotor = null;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftMotor = new Talon(Constants.m_leftMotorPort);
    rightMotor = new Talon(Constants.m_rightMotorPort);

  }


  public static void driveForward(double d){
    leftMotor.setSpeed(d);
    rightMotor.setSpeed(d);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run



  }
}
