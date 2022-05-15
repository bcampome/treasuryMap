package bdd;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tm.domain.Direction;
import tm.domain.Instruction;
import tm.domain.Adventurer;
import tm.domain.Position;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AdventurerStepsDef {

    private Adventurer adventurer;

    @Given("An adventurer {string} was created")
    public void anAdventurerWasCreated(String name) {
        this.adventurer = new Adventurer(name, Direction.East);

    }

    @When("the adventurer receive as next instruction as {string}")
    public void theAdventurerReceiveAsNextInstructionAs(String instruction) {
        this.adventurer.addInstructions(instruction);
    }


    @ParameterType("MOVE_LEFT|MOVE_RIGHT|MOVE_FORWARD")
    public Instruction instruction(String action) {
        return Instruction.getInstructionByValue(action);
    }

    @Then("the adventurer should apply next instruction {}")
    public void theAdventurerShouldApplyNextInstructionAction(Instruction expected) {
        Optional<Instruction> instruction = this.adventurer.applyNextInstruction();
        assertTrue(instruction.isPresent());
        assertEquals(expected, instruction.get());
    }


    @Given("An adventurer {string} was created with direction {string}")
    public void anAdventurerJohnWasCreatedWithDirectionDirection(String name, String direction) {
        this.adventurer = new Adventurer(name, Direction.getDirectionByValue(direction));
    }

    @And("the adventurer received next instruction as {string}")
    public void theAdventurerReceivedNextInstructionAs(String instruction) {
        this.adventurer.addInstructions(instruction);
    }

    @When("the adventurer apply next instruction")
    public void theAdventurerApplyNextInstruction() {
        this.adventurer.applyNextInstruction();
    }

    @Then("the adventurer should have direction as {string}")
    public void theAdventurerShouldHaveDirectionAs(String direction) {
        assertEquals(direction, this.adventurer.getDirection().value());
    }

    @And("the adventurer was in position \\(column : {int} line : {int})")
    public void theAdventurerWasInPositionColumnLine(int column, int line) {
        this.adventurer.setCurrentPosition(new Position(column, line));
    }

    @Then("the adventurer should have desired position as \\(column : {int}, line : {int})")
    public void theAdventurerShouldHaveDesiredPositionAsColumnColumnLineLine(int column, int line) {
        Optional<Position> desired = this.adventurer.getDesiredPosition();
        assertTrue(desired.isPresent());
        assertEquals(new Position(column, line), desired.get());
    }

    @Then("The adventurer should not have desired position")
    public void theAdventurerShouldNotHaveDesiredPosition() {
        Optional<Position> desiredPosition = this.adventurer.getDesiredPosition();
        assertTrue(desiredPosition.isEmpty());
    }

    @When("the adventurer finds a treasury of {int}")
    public void theAdventurerFindsATreasuryOf(int treasury) {
        adventurer.handleTreasury(treasury);
    }


    @And("the adventurer found a treasury of {int}")
    public void theAdventurerFoundATreasuryOf(int treasury) {
        adventurer.handleTreasury(treasury);
    }

    @Then("the adventurer should provide {int} as sum of treasures he found")
    public void theAdventurerShouldProvideAsSumOfTreasuresHeFound(int amount) {
        assertEquals(amount, adventurer.getSumOfTreasury());
    }

    @When("the adventurer is in position \\(column : {int} line : {int})")
    public void theAdventurerIsInPositionColumnLine(int column, int line) {
        adventurer.setCurrentPosition(new Position(column,line));
    }

    @Then("the adventurer should provide current position as \\(column : {int}, line : {int})")
    public void theAdventurerShouldProvideCurrentPositionAsColumnLine(int column, int line) {
        assertEquals(new Position(1,1),adventurer.getCurrentPosition());
    }
}



