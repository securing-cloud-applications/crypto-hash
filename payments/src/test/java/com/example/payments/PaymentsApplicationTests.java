package com.example.payments;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentsApplicationTests {

  @Autowired private PaymentService paymentService;

  @Test
  void testRefundProcessing() {
    paymentService.processRefunds(Path.of("../data/refunds.json"));
  }
}
