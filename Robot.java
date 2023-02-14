// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.ModuleLayer.Controller;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first star000ted up and should be used for any
   * initialization code.
   */

   //declares the controllers and buttons

  //  XboxController controller = new XboxController(0);

    Joystick controller = new Joystick(0);
   //XboxController.Button kA;


   //declares motors and their Id and type 
   private CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
   private CANSparkMax frontRight = new CANSparkMax(3, MotorType.kBrushless);
   private CANSparkMax backLeft = new CANSparkMax(2, MotorType.kBrushless);
   private CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);
   
   //other objects
   private RelativeEncoder encoderFL = frontLeft.getEncoder();
   private RelativeEncoder encoderFR = frontRight.getEncoder();
   private RelativeEncoder encoderBL = backLeft.getEncoder();
   private RelativeEncoder encoderBR = backRight.getEncoder();

   ShuffleboardTab tab = Shuffleboard.getTab("testTab");

   //groups left and right motor groups together
   MotorControllerGroup leftSide = new MotorControllerGroup(backLeft, frontLeft);
   MotorControllerGroup rightSide = new MotorControllerGroup(backRight, frontRight);

   DifferentialDrive motorDrive = new DifferentialDrive(rightSide, leftSide);

   private double targetSpeed;


   
  @Override
  public void robotInit() {}

  protected void execute(){

    // SmartDashboard.putNumber("MotorFR", encoderFR.getPosition());
    // SmartDashboard.putNumber("MotorFL", encoderFL.getPosition());

    // SmartDashboard.putNumber("MotorBR", encoderBR.getPosition());
    // SmartDashboard.putNumber("MotorBL", encoderBL.getPosition());


  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("MotorFR", encoderFR.getPosition());
    SmartDashboard.putNumber("MotorFL", encoderFL.getPosition());

    SmartDashboard.putNumber("MotorBR", encoderBR.getPosition());
    SmartDashboard.putNumber("MotorBL", encoderBL.getPosition());



  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {
    

  }

  @Override
  public void teleopInit() {

    rightSide.setInverted(true);
    leftSide.setInverted(false);

  }

  @Override
  public void teleopPeriodic() {
    
    double speed = -controller.getRawAxis(1) * 0.50; //reads left y axis
    double turn = controller.getRawAxis(4) * 0.50;

    motorDrive.arcadeDrive(speed, turn);



  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}  