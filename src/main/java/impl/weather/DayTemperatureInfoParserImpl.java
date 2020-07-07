package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.DayTemperatureInfoParser;

import java.time.Month;

public class DayTemperatureInfoParserImpl implements DayTemperatureInfoParser {
    @Override
    public DayTemperatureInfo parse(String expression) {

        int stringLength = expression.length();
        StringBuilder parsedDayBuilder = new StringBuilder();
        StringBuilder parsedMonthBuilder = new StringBuilder();
        StringBuilder parsedTemperatureBuilder = new StringBuilder();
        int separatorCount = 0;
        boolean negativeTemperature = false;

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {
            char currentElement = expression.charAt(stringIndex);
            if (Character.isWhitespace(currentElement) || currentElement == '.') {
                separatorCount++;
            } else {
                if (separatorCount == 0) {
                    parsedDayBuilder.append(currentElement);
                }
                if (separatorCount == 1) {
                    parsedMonthBuilder.append(currentElement);
                }
                if (separatorCount == 2) {
                    if (currentElement == '-') {
                        negativeTemperature = true;
                    } else parsedTemperatureBuilder.append(currentElement);
                }
            }
        }
        String parsedDayString = parsedDayBuilder.toString();
        String parsedMonthString = parsedMonthBuilder.toString();
        String parsedTemperatureString = parsedTemperatureBuilder.toString();
        int parsedDay = Integer.parseInt(parsedDayString);
        int parsedMonth = Integer.parseInt(parsedMonthString);
        Month month = Month.of(parsedMonth);
        int parsedTemperature = Integer.parseInt(parsedTemperatureString);
        if (negativeTemperature) {
            parsedTemperature *= -1;
        }
        return new DayTemperatureInfoImpl(parsedDay, month, parsedTemperature);
    }
}
