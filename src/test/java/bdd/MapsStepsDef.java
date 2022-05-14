package bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tm.domain.Map;
import tm.domain.ItemMap;
import tm.domain.Mountain;
import tm.domain.Treasury;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class MapsStepsDef {

    private Map map;
    private ItemMap lastItemMap = null;

    @Given("Received a creation map file")
    public void receivedACreationMapFile() {
        // Nothing to do.
    }

    @Then("the map should have no treasury")
    public void theMapHasNoTreasury() {
        assertEquals(0, map.getNumberOfTreasuryAvailable());
    }

    @And("the map should have no mountain")
    public void theMapHasNoMountain() {
        assertEquals(0, map.getNumbersOfMountains());
    }

    @When("the description start with C {int} {int}")
    public void theDescriptionStartWithMapCreation(int columnSize, int lineSize) {
        this.map = new Map(columnSize, lineSize);
    }

    @And("the description contains T {int}-{int} {int}")
    public void theDescriptionContainTreasuryCreation(int column, int line, int numberOfTreasury) {
        map.addTreasury(numberOfTreasury, column, line);
    }

    @And("the map should contain {int} treasury in \\(column:{int}, line: {int})")
    public void theMapContainTreasuryInColumnLine(int numberOfTreasury, int column, int line) {
        Optional<ItemMap> item = map.getItem(column, line);
        assertTrue(item.isPresent());
        if (item.get() instanceof Treasury treasury) {
            assertEquals(numberOfTreasury, treasury.getSize());
        } else {
            throw new AssertionError();
        }
    }

    @And("the description contains M {int}-{int}")
    public void theDescriptionContainMountainCreation(int column, int line) {
        map.addMountain(column, line);

    }

    @Then("the map should have a mountain in \\(column:{int}, line: {int})")
    public void theMapHasMountainInColumnLine(int column, int line) {
        Optional<ItemMap> item = map.getItem(column, line);
        assertTrue(item.isPresent());
        assertInstanceOf(Mountain.class, item.get());
    }

    @And("we extract the item in \\(column:{int}, line: {int})")
    public void weExtractTheItemInColumnLine(int column, int line) {
        Optional<ItemMap> itemMap = map.retrieve(column, line);
        assertTrue(itemMap.isPresent());
        lastItemMap = itemMap.get();
    }


    @Then("the case in \\(column:{int}, line: {int}) should be empty")
    public void theCaseInColumnLineIsEmpty(int column, int line) {
        assertTrue(map.getItem(column, line).isEmpty());
    }

    @And("the last item extracted should be a treasury with value of {int}")
    public void theLastItemExtractedIsATreasuryWithValueOf(int value) {;
        if (lastItemMap instanceof Treasury treasury) {
            assertEquals(value, treasury.getSize());
        } else {
            throw new AssertionError();
        }
    }
}
