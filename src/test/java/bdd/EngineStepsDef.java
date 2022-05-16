package bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tm.builder.AdventurerBuilder;
import tm.builder.MapBuilder;
import tm.domain.Adventurer;
import tm.domain.Map;
import tm.domain.Position;
import tm.engine.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EngineStepsDef {

    private Map map;
    private Engine engine;
    private List<Adventurer> adventurers = new ArrayList<>();

    @Given("a map was created with {string}")
    public void aMapWasCreatedWith(String input) {
        this.map = MapBuilder.build(input);
    }

    @And("an adventurer was created with {string}")
    public void anAdventurerWasCreatedWith(String input) {
        adventurers.addAll(AdventurerBuilder.build(input));
    }

    @When("the engine run all instruction of adventurer\\(s)")
    public void theEngineRunAllInstructionOfAdventurerS() {
        engine = new Engine(map, adventurers);
        engine.run();
    }

    @Then("the adventurer {string} should be in position \\(column : {int}, line : {int})")
    public void theAdventurerShouldBeInPositionColumnLine(String adventurerName, int column, int line) {
        Optional<Adventurer> adventurer = engine.getAdventurers()
                .stream()
                .filter(adv -> adv.getName().equals(adventurerName))
                .findFirst();
        assertTrue(adventurer.isPresent());
        assertEquals(new Position(column,line),adventurer.get().getCurrentPosition());
    }

    @Then("the adventurer {string} should have treasurer sum equal to {int}")
    public void theAdventurerShouldHaveTreasurerSumEqualToTotalTreasury(String name, int treasurersAmount) {
        Optional<Adventurer> adventurer = engine.getAdventurers()
                .stream()
                .filter(adv -> adv.getName().equals(name))
                .findFirst();
        assertTrue(adventurer.isPresent());
        assertEquals(treasurersAmount, adventurer.get().getSumOfTreasury());
    }
}
