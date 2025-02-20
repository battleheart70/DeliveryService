package delivery.strategies;

import delivery.util.DateProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/** Factory for creating a list of pricing strategies. */
@Component
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
        new WeekendPricing(),
        new WorkloadPricing());
  }
}
