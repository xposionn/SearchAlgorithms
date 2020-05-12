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
}
