package delivery;

import delivery.exceptions.FragileItemDistanceExceededException;
import delivery.strategies.PricingStrategy;
import delivery.strategies.PricingStrategyFactory;
import delivery.validation.DeliveryValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

import static delivery.constants.DeliveryConstants.MIN_COST;

/**
 * Service for calculating delivery costs.
 */
@Service
public class DeliveryService {
  private final List<PricingStrategy> strategies;

  /**
   * Constructor for DeliveryService.
   *
   * @param strategyFactory the factory for pricing strategies
   */
  public DeliveryService(PricingStrategyFactory strategyFactory) {
    this.strategies = strategyFactory.getStrategies();
  }

  /**
   * Calculates the delivery cost based on the request.
   *
   * @param request the delivery request
   * @return the delivery cost
   * @throws FragileItemDistanceExceededException if the distance for fragile items exceeds the allowed limit
   */
  public int calculateDeliveryCost(DeliveryRequest request) throws FragileItemDistanceExceededException {
    DeliveryValidatorUtil.validate(request);
    int price = 0;
    for (PricingStrategy strategy : strategies) {
      if (strategy.isApplicable(request)){
        price = strategy.calculate(price, request);
      }
    }
    return Math.max(price, MIN_COST);
  }
}