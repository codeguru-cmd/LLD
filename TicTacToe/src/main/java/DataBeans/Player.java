package DataBeans;

import lombok.Data;

@Data
public class Player {

    String name;
    char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
