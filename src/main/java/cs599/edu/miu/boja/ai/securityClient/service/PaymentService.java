package cs599.edu.miu.boja.ai.securityClient.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import cs599.edu.miu.boja.ai.securityClient.exception.PaymentProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 2/8/25
 */

@Service
@Slf4j
public class PaymentService {

    public String createPaymentIntent(Long amount) {
        try {
            PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
                    .setCurrency("USD")
                    .setAmount(amount)
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return paymentIntent.getClientSecret();
        } catch (StripeException exception) {
            log.error("Error processing payment: {}", exception.getMessage());
            throw new PaymentProcessingException("Failed to process payment: " + exception.getMessage(), exception);

        }

    }
}

