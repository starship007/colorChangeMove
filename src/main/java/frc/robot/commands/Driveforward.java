/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
public class Driveforward extends CommandBase {
  /**
   * Creates a new Driveforward.
   */
  public Driveforward() {
    addRequirements(Robot.m_Drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Driveforward has been initialized", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if (Robot.numberOfChange % 2 == 0) {
        System.out.println("number of changes is even");
        Drivetrain.driveForward(0.1);
      }else{
        System.out.println("number of changes is odd");
        Drivetrain.driveForward(0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
