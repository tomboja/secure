package cs599.edu.miu.boja.ai.securityClient.domain.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 2/8/25
 */

@Data
@Builder
public class CustomErrorResponse {
    private int status;
    private String message;
    private String error;
    private String path;
    private LocalDateTime timestamp;
}
