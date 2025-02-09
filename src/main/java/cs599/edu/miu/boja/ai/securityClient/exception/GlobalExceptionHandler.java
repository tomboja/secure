package cs599.edu.miu.boja.ai.securityClient.exception;

import com.stripe.exception.StripeException;
import cs599.edu.miu.boja.ai.securityClient.domain.exception.CustomErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 2/8/25
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<CustomErrorResponse> handleStripeException(StripeException exception, HttpServletRequest request) {
        log.error("Stripe exception occurred: {}", exception.getMessage());
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Payment processing failed: " + exception.getMessage())
                .error(exception.getClass().getSimpleName())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(Exception exception, HttpServletRequest request) {
        log.error("Runtime exception occurred: {}", exception.getMessage());
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An error occurred: " + exception.getMessage())
                .error(exception.getClass().getSimpleName())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<CustomErrorResponse> handlePaymentException(PaymentProcessingException e, HttpServletRequest request) {
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .error(e.getClass().getSimpleName())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
