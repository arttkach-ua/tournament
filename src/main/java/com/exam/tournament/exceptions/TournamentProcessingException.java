package com.exam.tournament.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TournamentProcessingException extends RuntimeException{
    private static final String ERROR_MESSAGE = "Tournament processing exception was thrown";

        public TournamentProcessingException() {
            log.error(ERROR_MESSAGE);
        }

        public TournamentProcessingException(String message) {
            super(message);
            log.error(ERROR_MESSAGE, message);
        }

        public TournamentProcessingException(String message, Throwable cause) {
            super(message, cause);
            log.error(ERROR_MESSAGE, message, cause);
        }

        public TournamentProcessingException(Throwable cause) {
            super(cause);
        }

        public TournamentProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
            log.error(ERROR_MESSAGE, message, cause);
        }
}
