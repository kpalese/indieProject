package com.tasktracker.persistence;

import com.calendarific.HolidaysItem;
import com.calendarific.Response;
import com.calendarific.Results;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasktracker.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

public class CalendarificAPI implements PropertiesLoader {
    final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties = loadProperties("/tasktracker.properties");

    public CalendarificAPI() throws Exception {
    }

    /**
     * Gets national holidays for a particular year from the calendarific api
     * @param year the year for which holidays are requested
     * @return calendarificResults
     */
    public Results getCalendarificResults(String year) {
        String apiKey = properties.getProperty("calendarific.api.key");

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://calendarific.com/api/v2/holidays/json?api_key=" + apiKey + "&country=US&type=national&year=" + year);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Results calendarificResults = null;
        try {
            calendarificResults = mapper.readValue(response, Results.class);
        } catch (JsonProcessingException e) {
            logger.debug("CalendarificAPI: error processing JSON response");
            e.printStackTrace();
        }
        return calendarificResults;
    }
}
