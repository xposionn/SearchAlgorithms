package Game;

import java.awt.*;

public class Tile {

    private String _value;
    private Color _color;

    public Tile(String value,Color color) {
        this._value = value;
        this._color = color;
    }

    @Override
    public String toString() {
        return _value;
    }

    public String get_value() {
        return _value;
    }

    public Color get_color() {
        return _color;
    }

    public boolean equals(Tile oth) {
        return _value.equals(oth.get_value()) && _color.equals(oth.get_color());
    }

}
