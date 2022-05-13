package tm.domain;

public enum Instruction {
    ROTATE_LEFT("G"),
    ROTATE_RIGHT("D"),
    MOVE_FORWARD("A");

    private final String value;

    Instruction(String value) {
        this.value = value;
    }

    public static Instruction getInstructionByValue(String value){
        for(Instruction instruction : Instruction.values()){
            if (instruction.value.equals(value)){
                return instruction;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
