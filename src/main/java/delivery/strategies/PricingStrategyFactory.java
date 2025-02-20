package delivery.strategies;

import java.util.Arrays;
import java.util.List;

/**
 * Factory for creating a list of pricing strategies.
 */
public class PricingStrategyFactory {

  /**
   * Returns a list of all available pricing strategies.
   *
   * @return list of pricing strategies
   */
  public List<PricingStrategy> getStrategies() {
    return Arrays.asList(
        new DistanceStrategy(),
        new CargoSizePricing(),
        new UrgencyPricing(),
        new FragilityPricing(),
        new WorkloadPricing());
  }
}