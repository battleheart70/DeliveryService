package delivery.models;

import static delivery.constants.DeliveryConstants.*;

public enum Workload {
  VERY_HIGH(WORKLOAD_VERY_HIGH_MULTIPLIER),
  HIGH(WORKLOAD_HIGH_MULTIPLIER),
  ELEVATED(WORKLOAD_ELEVATED_MULTIPLIER),
  NORMAL(WORKLOAD_NORMAL_MULTIPLIER);

  private final double multiplier;

  Workload(double multiplier) {
    this.multiplier = multiplier;
  }

  public double getMultiplier() {
    return multiplier;
  }

  @Override
  public String toString() {
    return "For " + name() + " service workload multiplier is: " + multiplier;
  }
}
