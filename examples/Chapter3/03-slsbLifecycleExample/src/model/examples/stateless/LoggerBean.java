package examples.stateless;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class LoggerBean {
    private Logger logger;

    @PostConstruct
    private void init() {
        logger = Logger.getLogger("notification");
    }

    public void logMessage(String message) {
        logger.info(message);
    }
}

