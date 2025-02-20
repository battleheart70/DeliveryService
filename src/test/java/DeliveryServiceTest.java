import delivery.DeliveryService;
import delivery.DeliveryRequest;
import delivery.exceptions.FragileItemDistanceExceededException;
import delivery.models.CargoSize;
import delivery.models.Urgency;
import delivery.models.Workload;
import delivery.strategies.PricingStrategyFactory;
import delivery.util.DateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class DeliveryServiceTest {
  private final DeliveryService deliveryService = new DeliveryService(new PricingStrategyFactory());

  @ParameterizedTest
  @DisplayName("Проверка стоимости доставки в будний день")
  @Tag("Positive")
  @CsvSource({
          "NORMAL, 0.2, SMALL, false, STANDARD, 400",
          "HIGH, 14.3, LARGE, true, EXPRESS, 1050",
          "VERY_HIGH, 35, LARGE, false, SAME_DAY, 1280",
          "HIGH, 2, SMALL, true, STANDARD, 630",
          "NORMAL, 10, LARGE, false, EXPRESS, 400",
          "HIGH, 29.9, SMALL, true, SAME_DAY, 1260",
          "HIGH, 30, SMALL, true, STANDARD, 840",
          "VERY_HIGH, 9.9, LARGE, false, EXPRESS, 560",
          "HIGH, 10.1, SMALL, true, SAME_DAY, 1260",
          "NORMAL, 1.9, LARGE, false, STANDARD, 400",
          "HIGH, 29.9, SMALL, true, EXPRESS, 910",
          "NORMAL, 2, LARGE, false, SAME_DAY, 550",
          "NORMAL, 99.9, LARGE, false, SAME_DAY, 800",
          "HIGH, 100, SMALL, false, SAME_DAY, 980"
  })
  void getDeliveryCost_ValidInputs_ReturnsExpectedCostOnWeekdays(
          Workload workload,
          double distance,
          CargoSize cargoSize,
          boolean isFragile,
          Urgency urgency,
          int expectedCost)
          throws FragileItemDistanceExceededException {
    try (MockedStatic<DateProvider> mockedDateProvider = mockStatic(DateProvider.class)) {
      LocalDate fixedDate = LocalDate.of(2025, 2, 19); // Wednesday
      mockedDateProvider.when(DateProvider::now).thenReturn(fixedDate);
      DeliveryRequest request = new DeliveryRequest(distance, isFragile, cargoSize, workload, urgency);
      int cost = deliveryService.calculateDeliveryCost(request);
      assertEquals(expectedCost, cost);
    }
  }

  @ParameterizedTest
  @DisplayName("Проверка стоимости доставки в выходной день")
  @Tag("Positive")
  @CsvSource({
          "NORMAL, 0.2, SMALL, false, STANDARD, 400",
          "HIGH, 14.3, LARGE, true, EXPRESS, 1225",
          "VERY_HIGH, 35, LARGE, false, SAME_DAY, 1480",
          "HIGH, 2, SMALL, true, STANDARD, 805",
          "NORMAL, 10, LARGE, false, EXPRESS, 475",
          "HIGH, 29.9, SMALL, true, SAME_DAY, 1435",
          "HIGH, 30, SMALL, true, STANDARD, 1015",
          "VERY_HIGH, 9.9, LARGE, false, EXPRESS, 760",
          "HIGH, 10.1, SMALL, true, SAME_DAY, 1435",
          "NORMAL, 1.9, LARGE, false, STANDARD, 400",
          "HIGH, 29.9, SMALL, true, EXPRESS, 1085",
          "NORMAL, 2, LARGE, false, SAME_DAY, 675",
          "NORMAL, 99.9, LARGE, false, SAME_DAY, 925",
          "HIGH, 100, SMALL, false, SAME_DAY, 1155"
  })
  void getDeliveryCost_ValidInputs_ReturnsExpectedCostOnWeekends(
          Workload workload,
          double distance,
          CargoSize cargoSize,
          boolean isFragile,
          Urgency urgency,
          int expectedCost)
          throws FragileItemDistanceExceededException {
    try (MockedStatic<DateProvider> mockedDateProvider = mockStatic(DateProvider.class)) {
      LocalDate fixedDate = LocalDate.of(2025, 2, 22); // Saturday
      mockedDateProvider.when(DateProvider::now).thenReturn(fixedDate);
      DeliveryRequest request = new DeliveryRequest(distance, isFragile, cargoSize, workload, urgency);
      int cost = deliveryService.calculateDeliveryCost(request);
      assertEquals(expectedCost, cost);
    }
  }

  @Test
  @DisplayName("Проверка отказа на перевозку хрупкого груза на большое расстояние")
  @Tag("CustomException")
  void getDeliveryCost_InvalidInputs_ThrowsFragileItemDistanceExceededException() {
    assertThrows(
            delivery.exceptions.FragileItemDistanceExceededException.class,
            () -> {
              DeliveryRequest request = new DeliveryRequest(35, true, CargoSize.LARGE, Workload.HIGH, Urgency.EXPRESS);
              deliveryService.calculateDeliveryCost(request);
            },
            "Хрупкий груз нельзя перевозить дальше 30 км!");
  }
}
