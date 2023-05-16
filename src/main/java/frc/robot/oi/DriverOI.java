package frc.robot.oi;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.oi.DriverOI;

public class DriverOI {
    private XboxController m_controller;

    private JoystickButton m_toggleIntakeMotor;
    private JoystickButton m_toggleFeederMotor;
    private JoystickButton m_shiftLow;
    private JoystickButton m_shiftHigh;
    private JoystickButton m_turnTurretToTarget;
    private JoystickButton m_incrementFlywheel;
    private JoystickButton m_decrementFlywheel;
    private JoystickButton m_toggleFlywheel;
    private JoystickButton m_increaseFlywheelButton;
    private Trigger m_turnTurretLeft;
    private Trigger m_turnTurretRight;
    private Trigger m_shiftButton;
    private Trigger m_intakeOutButton;

    public DriverOI(XboxController controller) {
        m_controller = controller;

        m_toggleIntakeMotor = new JoystickButton(m_controller, XboxController.Button.kY.value);
        m_toggleFeederMotor = new JoystickButton(m_controller, XboxController.Button.kY.value);
        m_shiftButton = new Trigger(() -> m_controller.getLeftStickButtonPressed());

        m_incrementFlywheel = new JoystickButton(m_controller, XboxController.Button.kStart.value);
        m_decrementFlywheel = new JoystickButton(m_controller, XboxController.Button.kBack.value);
        m_toggleFlywheel = new JoystickButton(m_controller, XboxController.Button.kY.value);
        
        m_turnTurretToTarget = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
        m_turnTurretRight = new Trigger(() -> m_controller.getPOV() == 90);
        m_turnTurretLeft = new Trigger(() -> m_controller.getPOV() == 270);

        m_intakeOutButton = new Trigger(() -> m_controller.getRightTriggerAxis() > 0.1);

        m_increaseFlywheelButton = new JoystickButton(m_controller, XboxController.Button.kX.value);
    }

    // ---------------- Intake ----------------------------

    public Trigger getToggleIntakeMotorButton(){
        return m_toggleIntakeMotor;
    }

    public Trigger getToggleFeederMotorButton(){
        return m_toggleFeederMotor;
    }

    public Trigger getIntakeOutButton(){
        return m_intakeOutButton;
    }

    // ---------------- Flywheel ----------------------------

    public Trigger getIncrementFlywheelButton(){
        return m_incrementFlywheel;
    }

    public Trigger getDecrementFlywheelButton(){
        return m_decrementFlywheel;
    }

    public Trigger getToggleFlywheelButton(){
        return m_toggleFlywheel;
    }

    // ---------------- Turret ---------------------------

    public DoubleSupplier getRotateTurretLeftSupplier() {
        return () -> m_controller.getLeftTriggerAxis();
    }

    public DoubleSupplier getRotateTurretRightSupplier() {
        return () -> m_controller.getRightTriggerAxis();
    }

    public Trigger getTurnTurretLeftButton(){
        return m_turnTurretLeft;
    }

    public Trigger getTurnTurretRightButton(){
        return m_turnTurretRight;
    }

    public Trigger getTurnTurretToTargetButton() {
        return m_turnTurretToTarget;
    }

    // ---------------- Drivetrain ----------------------------

    public Trigger getShiftButton(){
        return m_shiftButton;
    }

    public DoubleSupplier getMoveSupplier() {
        return () -> -m_controller.getLeftY();
    }

    public Trigger getIsAtHighSpeed() {
        return new Trigger(() -> Math.abs(m_controller.getLeftY()) > .85);
    }

    public DoubleSupplier getRotateSupplier() {
        return () -> m_controller.getRightX();
    }  
}