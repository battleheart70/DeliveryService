package delivery.strategies;

import delivery.DeliveryRequest;

public class CargoSizePricing implements PricingStrategy {

  @Override
  public boolean isApplicable(DeliveryRequest request) {
    return true;
  }

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return currentPrice + request.cargoSize().getCargoSizeAddition();
  }
}
