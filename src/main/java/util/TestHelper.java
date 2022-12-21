package util;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

public class TestHelper {
    public static Object getResponseObject(String responseString, Class responseClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(responseString, responseClass);
    }

    public static String getJsonString(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRandomNumber (){
        return RandomStringUtils.randomNumeric(3);
    }

    public static String getRandomPhoneNumber (){
        return RandomStringUtils.randomNumeric(10);
    }

    public static int getRandomNumberInt (){
        Random random = new Random();
        int n = random.nextInt(100);
        return n;
    }

    public static Object getUserResponseValue(Response response, String field){
        return response.jsonPath().get(field).toString();
    }

    public static boolean isInClosedRange(Long number, Long lowerBound, Long upperBound) {
        return (lowerBound <= number && number <= upperBound);
    }

    public static ZonedDateTime formatDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date _date=formatter.parse(date);
        final ZoneId id = ZoneId.of("UTC");
        return ZonedDateTime.ofInstant(_date.toInstant(), id);
    }

    public static OffsetDateTime formatDateToUTC() throws ParseException {
        OffsetDateTime now = OffsetDateTime.now( ZoneOffset.UTC );
        return  now;
    }

}
