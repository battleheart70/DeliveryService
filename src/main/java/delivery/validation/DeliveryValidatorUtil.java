package delivery.validation;

import delivery.DeliveryRequest;
import delivery.exceptions.FragileItemDistanceExceededException;

import static delivery.constants.DeliveryConstants.*;

public class DeliveryValidatorUtil {

  private DeliveryValidatorUtil() {
    throw new UnsupportedOperationException("Это утилитарный класс, невозможно создать экземпляр!");
  }

  public static void validate(DeliveryRequest deliveryRequest)
      throws FragileItemDistanceExceededException {
    if (deliveryRequest == null) {
      throw new IllegalArgumentException("Запрос на доставку не может быть null!");
    }

    if (deliveryRequest.distance() > MAX_DELIVERY_DISTANCE) {
      throw new IllegalArgumentException(
          "Расстояние не может быть больше " + MAX_DELIVERY_DISTANCE);
    }
    validateFragileItemDistance(deliveryRequest.distance(), deliveryRequest.isFragile());
  }

  public static void validateFragileItemDistance(double distance, boolean isFragile)
      throws FragileItemDistanceExceededException {
    if (isFragile && distance > MAX_FRAGILE_DISTANCE) {
      throw new FragileItemDistanceExceededException(INVALID_FRAGILE_DISTANCE_MESSAGE);
    }
  }
}
