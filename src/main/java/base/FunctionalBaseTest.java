package base;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public abstract class FunctionalBaseTest {


    protected GenericContainer esContainer;
    public static List<String> allValidUserNames = new ArrayList<>();


    public abstract void setupData() throws Exception;

    @BeforeSuite
    public void suiteSetUp() throws Exception {
        printStartMessage();
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        setupData();
    }

    @AfterSuite
    public void suiteTearDown() {
        printStopMessage();
    }


    protected void printStartMessage() {
        log.info("STARTING FUNCTIONAL SUITE");
    }

    protected void printStopMessage() {
        log.info("STOPPING FUNCTIONAL SUITE");
    }

    // ********************************************************************************************
    // Methods for building protocol, hostname, and port for URLs
    // ********************************************************************************************

    protected String getHostname() {
        return buildHostnameUrl();
    }

    private String getServiceHostname() {
        return System.getProperty("serviceHost", "https://petstore.swagger.io");
    }

    private String buildHostnameUrl() {
        String targetUrl;
        targetUrl = getServiceHostname();
        return targetUrl;
    }

    protected static void validUserNames(String validUsername){
        List<String> users = Arrays.asList(validUsername);
        users.stream()
                .forEach( u -> allValidUserNames.add(u));
    }

    protected static String getUserName(){
        //Fetch Random valid User Details
        Random random = new Random();
        int randomUserIndex = random.nextInt(allValidUserNames.size());
        String username = allValidUserNames.get(randomUserIndex);
        return username;
    }



}
