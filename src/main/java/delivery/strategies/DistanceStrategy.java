package delivery.strategies;

import delivery.DeliveryRequest;

import static delivery.constants.DeliveryConstants.*;

public class DistanceStrategy implements PricingStrategy {

  @Override
  public boolean isApplicable(DeliveryRequest request) {
    return true;
  }

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    double distance = request.distance();

    if (distance > HIGH_DISTANCE_THRESHOLD) return currentPrice + HIGH_DISTANCE_SURCHARGE;
    if (distance > MEDIUM_DISTANCE_THRESHOLD) return currentPrice + MEDIUM_DISTANCE_SURCHARGE;
    if (distance > LOW_DISTANCE_THRESHOLD) return currentPrice + LOW_DISTANCE_SURCHARGE;
    return currentPrice + BASE_DISTANCE_SURCHARGE;
  }

}
