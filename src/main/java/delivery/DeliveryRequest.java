package delivery;

import delivery.models.CargoSize;
import delivery.models.Urgency;
import delivery.models.Workload;

/**
 * Delivery request.
 * Contains information about distance, cargo fragility, cargo size, workload, and urgency.
 *
 * @param distance delivery distance
 * @param isFragile indicates if the cargo is fragile
 * @param cargoSize cargo size
 * @param workload workload
 * @param urgency urgency
 * @throws IllegalArgumentException if the distance is less than 0, or if cargoSize, workload, or urgency are null
 */
public record DeliveryRequest(
        double distance, boolean isFragile, CargoSize cargoSize, Workload workload, Urgency urgency) {
  public DeliveryRequest {
    if (distance < 0) throw new IllegalArgumentException("Distance must be ≥ 0");
    if (cargoSize == null) throw new IllegalArgumentException("CargoSize cannot be null");
    if (workload == null) throw new IllegalArgumentException("Workload cannot be null");
    if (urgency == null) throw new IllegalArgumentException("Urgency cannot be null");
  }
}