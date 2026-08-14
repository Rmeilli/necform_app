package org.sid.necform;

import org.junit.jupiter.api.Test;
import org.sid.necform.config.TestSecurityConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class NecformApplicationTests {

    @Test
    void contextLoads() {
    }

}
