package com.exam.tournament.util.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {
    public static final String TYPE_OF_FILE_NOT_SUPPORTED = "Type %s not supported";
    public static final String GAME_NOT_SUPPORTED = "Game %s is not supported. Please contact your administrator";
    public static final String WRONG_FILE_DATA = "Wrong file data";
    public static final String NULL_GAME_NAME = "Game name can't be null";
    public static final String NULL_GAME_TYPE = "Game type can't be null";
}
