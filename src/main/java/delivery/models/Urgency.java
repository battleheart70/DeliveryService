package delivery.models;

import static delivery.constants.DeliveryConstants.EXPRESS_URGENCY_SURCHARGE;
import static delivery.constants.DeliveryConstants.SAME_DAY_URGENCY_SURCHARGE;

public enum Urgency {
    STANDARD(0), EXPRESS(EXPRESS_URGENCY_SURCHARGE), SAME_DAY(SAME_DAY_URGENCY_SURCHARGE);

    private final int urgencyAddition;

    Urgency(int urgencyAddition) {
        this.urgencyAddition = urgencyAddition;
    }

    public int getUrgencyAddition() {
        return urgencyAddition;
    }

    @Override
    public String toString() {
        return "For " + name() + " delivery surcharge is " + urgencyAddition + " roubles";
    }
}
