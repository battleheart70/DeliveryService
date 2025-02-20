package delivery.strategies;

import delivery.DeliveryRequest;

public class WorkloadPricing implements PricingStrategy {
  @Override
  public boolean isApplicable(DeliveryRequest request) {
    return true;
  }

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return (int) Math.ceil(currentPrice * request.workload().getMultiplier());
  }
}
