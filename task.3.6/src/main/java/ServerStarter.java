import org.apache.log4j.xml.DOMConfigurator;
import server.Server;


public class ServerStarter {

    public static void main(String[] args) {
        /*
        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
        logger.fatal("Fatal");
         */
        DOMConfigurator.configure("log4j.xml");
        new Server();
    }
}