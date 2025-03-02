package delivery.strategies;

import delivery.DeliveryRequest;

/**
 * Interface for pricing strategy.
 * Defines methods for checking the applicability of the strategy and calculating the delivery cost.
 */
public interface PricingStrategy {

    /**
   * Checks if the strategy is applicable for the given delivery request.
   *
   * @param request the delivery request
   * @return true if the strategy is applicable, otherwise false
   */
  boolean isApplicable(DeliveryRequest request);

  /**
   * Calculates the delivery cost based on the current price and the delivery request.
   *
   * @param currentPrice the current price
   * @param request the delivery request
   * @return the calculated delivery cost
   */
  int calculate(int currentPrice, DeliveryRequest request);
}