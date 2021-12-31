package us.unfamousthomas.multiplayerappliances.enums;

import org.bukkit.ChatColor;

public enum JokeMessage {

    APPARENT("When does a joke turn into a dad joke?\nWhen it becomes apparent"),
    ORANGE("What’s orange and sounds like a parrot?\nA Carrot"),
    STICKY("What’s brown and sticky?\nA stick"),
    NUTS("I thought about going on an all-almond diet.\nBut that’s just nuts.");

    String message;
    JokeMessage(String message) {
        this.message = message;
    }

    public String getColorMessage() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
