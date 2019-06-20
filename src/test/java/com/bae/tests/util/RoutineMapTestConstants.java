package com.bae.tests.util;

import com.bae.persistence.domain.Routine;

public class RoutineMapTestConstants {
	public static final Routine TESTROUTINE1 = new Routine(1, "Sun Salutation", "Warm Up");
	public static final String TESTROUTINE1STR = "{\"routineID\":1,\"routineName\":\"Sun Salutation\",\"routineType\":\"Warm Up\"}";
	public static final Routine TESTROUTINE2 = new Routine(2, "Moon Salutation", "Cool Down");
	public static final String TESTROUTINE2STR = "{\"routineID\":2,\"routineName\":\"Moon Salutation\",\"routineType\":\"Cool Down\"}";
	public static final String TESTROUTINEUPDATESTR = "{\"routineID\":1,\"routineName\":\"Moon Salutation\",\"routineType\":\"Wind Down\"}";

}