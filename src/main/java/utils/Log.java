package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private Logger logger;

    private static Log log;

    private Log(){
        logger = LogManager.getLogger(Log.class);
    }

    public static Log getInstance(){
        if(log==null){
            log= new Log();
        }
        return log;
    }

    public void info(String message){

        logger.info(message);
    }

    public void debug(String message){
        logger.debug(message);
    }

    public void warn(String message){
          logger.warn(message);
    }

    public void error(String message){
        logger.error(message);
    }


}
