/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


//phoenix update


package frc.robot;

//importing basic libraries
import edu.wpi.first.wpilibj.I2C;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.util.Color;
//importing subsystems
import frc.robot.subsystems.Drivetrain;

//importing color libraries
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;



/**
 * This is a simple example to show how the REV Color Sensor V3 can be used to
 * detect pre-configured colors.
 */
public class Robot extends TimedRobot {
  //initializing subsystems
    private final Drivetrain m_Drivetrain = new Drivetrain();
    private final I2C.Port i2cPort = I2C.Port.kOnboard;//sets up the I2C port
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);//sets up a new sensor at the port
    private final ColorMatch m_colorMatcher = new ColorMatch();//sets up the color match system
  //creats target colors
   private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
   private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
   private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
   private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  //for the color change count vairables
   static int numberOfChange = 0;
   static boolean isColorTheInitial = true; //used to determine if the color has changed or not
   static boolean controlBooleanCode = true; //used as a control when counting the number of changes
   static boolean hasInitialColorBeenSet = false;
   static String initialColor; //used as a placeholder for what the first color the color sensors see is
   
   

   


  @Override
  public void robotInit() {
    System.out.println("This code is running");
    
    //establishes the targets
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    
  }

  @Override
  public void robotPeriodic() {
    final Color detectedColor = m_colorSensor.getColor();
    String colorString;
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);


  

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    if (!Robot.hasInitialColorBeenSet) {
      Robot.initialColor = colorString;
      Robot.hasInitialColorBeenSet = true;
    }

    if(!colorString.equals(Robot.initialColor)){
      if(Robot.controlBooleanCode == true){
        Robot.controlBooleanCode = false;
        Robot.numberOfChange++;
        System.out.println("The color was changed, and the color has changed " + Robot.numberOfChange + " times from " + Robot.initialColor );
        
      }        
  } else{
    Robot.controlBooleanCode = true;
  }




    SmartDashboard.putString("Initial Color", Robot.initialColor);
    SmartDashboard.putNumber("Number of changes from the inital color, " + Robot.initialColor, Robot.numberOfChange);
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }
}