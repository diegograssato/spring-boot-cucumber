package io.tpd.springbootcucumber.bagbasics;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.tpd.springbootcucumber.bagcommons.BagHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class BagCucumberStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(BagCucumberStepDefinitions.class);

    @Autowired
    private BagHttpClient bagHttpClient;

    @When("^I put (\\d+) (\\w+) in the bag$")
    public void i_put_something_in_the_bag(final int quantity, final String something) {
        IntStream.range(0, quantity)
                .peek(n -> log.info("Putting a {} in the bag, number {}", something, quantity))
                .map(ignore -> bagHttpClient.put(something))
                .forEach(statusCode -> assertThat(statusCode, equalTo(HttpStatus.CREATED.value())));
    }

    @Then("^the bag should contain only (\\d+) (\\w+)$")
    public void the_bag_should_contain_only_something(final int quantity, final String something) {
        assertNumberOfTimes(quantity, something, true);
    }

    @Then("^the bag should contain (\\d+) (\\w+)$")
    public void the_bag_should_contain_something(final int quantity, final String something) {
        assertNumberOfTimes(quantity, something, false);
    }

    private void assertNumberOfTimes(final int quantity, final String something, final boolean onlyThat) {
        final List<String> things = bagHttpClient.getContents().getThings();
        log.info("Expecting {} times {}. The bag contains {}", quantity, something, things);
        final int timesInList = Collections.frequency(things, something);
//        get("/things").then().statusCode(200).assertThat()
//                .body("data.leagueId", equalTo(35));

        assertThat(timesInList,equalTo(quantity));
        if (onlyThat) {
            assertThat(timesInList,equalTo(things.size()));
        }
    }

}
