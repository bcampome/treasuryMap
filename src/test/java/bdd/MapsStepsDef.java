package bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tm.domain.Map;
import tm.domain.MapItem;
import tm.domain.Mountain;
import tm.domain.Treasury;

import static org.junit.jupiter.api.Assertions.*;


public class MapsStepsDef {

    private Map map;

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

    @And("the description contain T {int}-{int} {int}")
    public void theDescriptionContainTreasuryCreation(int numberOfTreasury, int column, int line) {
        map.addTreasury(numberOfTreasury, column, line);
    }

    @And("the map contain {int} treasury in \\(column:{int}, line: {int})")
    public void theMapContainTreasuryInColumnLine(int numberOfTreasury, int column, int line) {
        MapItem item = map.getItem(column, line);
        if (item instanceof Treasury treasury) {
            assertEquals(numberOfTreasury, treasury.getSize());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @And("the description contain M {int}-{int}")
    public void theDescriptionContainMountainCreation(int column, int line) {
        map.addMountain( column, line);

    }

    @Then("the map has a mountain in \\(column:{int}, line: {int})")
    public void theMapHasMountainInColumnLine(int column, int line) {
        assertInstanceOf(Mountain.class,map.getItem(column, line));
    }
}
