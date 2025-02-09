package cs599.edu.miu.boja.ai.securityClient.controller;

import cs599.edu.miu.boja.ai.securityClient.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 2/8/25
 */

@Slf4j
@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> createPaymentIntent(Long amount) {
        log.info("Processing payment...");

        String paymentIntentId = paymentService.createPaymentIntent(amount);

        return ResponseEntity.ok(paymentIntentId);
    }
}
