package delivery.constants;

public final class DeliveryConstants {
  private DeliveryConstants() {
    throw new UnsupportedOperationException("Utility class");
  }
  //Minimal cost of delivery
  public static final int MIN_COST = 400;
  //Fragile Item Shit
  public static final int FRAGILE_SURCHARGE = 300;
  public static final int MAX_FRAGILE_DISTANCE = 30;
  public static final String INVALID_FRAGILE_DISTANCE_MESSAGE ="Хрупкий груз нельзя перевозить дальше " + MAX_FRAGILE_DISTANCE + " км!";
  //Cargo Size Additions
  public static final int LARGE_CARGO_SIZE_SURCHARGE = 200;
  public static final int SMALL_CARGO_SIZE_SURCHARGE = 100;
  //Urgency Charges - default = 0
  public static final int EXPRESS_URGENCY_SURCHARGE = 50;
  public static final int SAME_DAY_URGENCY_SURCHARGE = 300;
  //Workload Multipliers
  public static final double WORKLOAD_VERY_HIGH_MULTIPLIER = 1.6;
  public static final double WORKLOAD_HIGH_MULTIPLIER = 1.4;
  public static final double WORKLOAD_ELEVATED_MULTIPLIER = 1.2;
  public static final double WORKLOAD_NORMAL_MULTIPLIER = 1.0;
  // threshold for distance
  public static final double LOW_DISTANCE_THRESHOLD = 2.0;
  public static final double MEDIUM_DISTANCE_THRESHOLD = 10.0;
  public static final double HIGH_DISTANCE_THRESHOLD = 30.0;
  // surcharges for distance
  public static final int BASE_DISTANCE_SURCHARGE = 50;
  public static final int LOW_DISTANCE_SURCHARGE = 100;
  public static final int MEDIUM_DISTANCE_SURCHARGE = 200;
  public static final int HIGH_DISTANCE_SURCHARGE = 300;

  // maxiumum delivery distance
  public static final double MAX_DELIVERY_DISTANCE = 100.0;

}
