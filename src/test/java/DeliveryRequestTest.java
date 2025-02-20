import delivery.DeliveryRequest;
import delivery.models.CargoSize;
import delivery.models.Urgency;
import delivery.models.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryRequestTest {
    @Test
    @DisplayName("Проверка null и пустых значений cargoSize")
    void getDeliveryCost_NullOrEmptyCargoSize_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(10, false, null, Workload.NORMAL, Urgency.STANDARD),"CargoSize не может быть null");

    }
    @Test
    @DisplayName("Проверка null и пустых значений workload")
    void getDeliveryCost_NullOrEmptyWorkload_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(10, false, CargoSize.SMALL, null, Urgency.EXPRESS),"Workload не может быть null");

    }
    @Test
    @DisplayName("Проверка null и пустых значений workload")
    void getDeliveryCost_NullOrEmptyUrgency_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(10, false, CargoSize.SMALL, Workload.HIGH, null),"Urgency не может быть null");

    }

    @Test
    @DisplayName("Проверка отрицательного расстояния")
    void getDeliveryCost_NegativeDistance_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(-5, false, CargoSize.SMALL, Workload.NORMAL, Urgency.SAME_DAY),"Расстояние должно быть ≥ 0");
    }
}