package cs599.edu.miu.boja.ai.securityClient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 2/8/25
 */

@Configuration
@ConfigurationProperties(prefix = "stripe.payment")
@Data
public class StripeConfig {

    private String secretKey;

}
