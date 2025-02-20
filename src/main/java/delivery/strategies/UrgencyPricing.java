package delivery.strategies;

import delivery.DeliveryRequest;
import delivery.models.Urgency;

public class UrgencyPricing implements PricingStrategy{
    @Override
    public boolean isApplicable(DeliveryRequest request) {
        return request.urgency() != Urgency.STANDARD;
    }

    @Override
    public int calculate(int currentPrice, DeliveryRequest request) {
        return currentPrice + request.urgency().getUrgencyAddition();
    }
}
