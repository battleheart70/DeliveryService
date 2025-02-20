package delivery.controller;

import delivery.DeliveryRequest;
import delivery.DeliveryService;
import delivery.exceptions.FragileItemDistanceExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@CrossOrigin(origins = "*")
public class DeliveryController {
  private final DeliveryService deliveryService;

  public DeliveryController(DeliveryService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @PostMapping("/calculate")
  public ResponseEntity<Integer> calculateDeliveryCost(@RequestBody DeliveryRequest request)
      throws FragileItemDistanceExceededException {
    int cost = deliveryService.calculateDeliveryCost(request);
    return ResponseEntity.ok(cost);
  }
}
