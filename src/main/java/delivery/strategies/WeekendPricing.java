package delivery.strategies;

import delivery.DeliveryRequest;
import delivery.constants.DeliveryConstants;
import delivery.util.DateProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendPricing implements PricingStrategy {

  @Override
  public boolean isApplicable(DeliveryRequest request) {
    LocalDate today = DateProvider.now();
    return today.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue();
  }

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return currentPrice + DeliveryConstants.WEEKEND_SURCHARGE;
  }
}
