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
    private Optional<ItemMap> lastItemMap = Optional.empty();

    @Given("Received a creation map file")
    public void receivedACreationMapFile() {
        // Nothing to do.
    }

    @Then("the map has no treasury")
    public void theMapHasNoTreasury() {
        assertEquals(0, map.getNumberOfTreasuryAvailable());
    }

    @And("the map has no mountain")
    public void theMapHasNoMountain() {
        assertEquals(0, map.getNumbersOfMountains());
    }

    @When("the description start with C {int} {int}")
    public void theDescriptionStartWithMapCreation(int columnSize, int lineSize) {
        this.map = new Map(columnSize, lineSize);
    }

    @And("the description contains T {int}-{int} {int}")
    public void theDescriptionContainTreasuryCreation(int column, int line, int numberOfTreasury) {
        System.out.println("column : " + column + " , line : " + line + ", number : " + numberOfTreasury);
        map.addTreasury(numberOfTreasury, column, line);
    }

    @And("the map contain {int} treasury in \\(column:{int}, line: {int})")
    public void theMapContainTreasuryInColumnLine(int numberOfTreasury, int column, int line) {
        ItemMap item = map.getItem(column, line);
        if (item instanceof Treasury treasury) {
            assertEquals(numberOfTreasury, treasury.getSize());
        } else {
            throw new AssertionError();
        }
    }

    @And("the description contains M {int}-{int}")
    public void theDescriptionContainMountainCreation(int column, int line) {
        map.addMountain(column, line);

    }

    @Then("the map has a mountain in \\(column:{int}, line: {int})")
    public void theMapHasMountainInColumnLine(int column, int line) {
        assertInstanceOf(Mountain.class, map.getItem(column, line));
    }

    @And("we extract the item in \\(column:{int}, line: {int})")
    public void weExtractTheItemInColumnLine(int column, int line) {
        lastItemMap = map.retrieve(column, line);
    }


    @Then("the case in \\(column:{int}, line: {int}) is empty")
    public void theCaseInColumnLineIsEmpty(int column, int line) {
        assertNull(map.getItem(column, line));
    }

    @And("the last item extracted is a treasury with value of {int}")
    public void theLastItemExtractedIsATreasuryWithValueOf(int value) {
        assertTrue(lastItemMap.isPresent());
        ItemMap itemMap = lastItemMap.get();
        if(itemMap instanceof Treasury treasury){
            assertEquals(value,treasury.getSize());
        }else {
        throw new AssertionError();
        }
    }
}
