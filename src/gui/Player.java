package gui;

public class Player {
    private String name;
    private boolean isMatched;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
