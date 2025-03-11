package be.kdg.prog6.MatcherContext.adapters.in;

public class HUResponseDTO {
    private String huNumber;

    public HUResponseDTO(String huNumber) {
        this.huNumber = huNumber;
    }

    public String getHuNumber() {
        return huNumber;
    }

    public void setHuNumber(String huNumber) {
        this.huNumber = huNumber;
    }
}
