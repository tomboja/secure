package cs599.edu.miu.boja.ai.securityClient;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SecureClientMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SecureClientMain.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
