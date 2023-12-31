package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("door", new Tile(4, 9));
        tileMap.put("open_door", new Tile(6, 9));
        tileMap.put("blue_door", new Tile(0, 9));
        tileMap.put("blue_open_door", new Tile(2, 9));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("pickaxe", new Tile(11,27));
        tileMap.put("destructible_wall", new Tile(12,17));
        tileMap.put("crown", new Tile(12, 24));
        tileMap.put("blue_key", new Tile(17, 23));
        tileMap.put("shield", new Tile(7, 26));
        tileMap.put("cat", new Tile(29, 7));
        tileMap.put("cow", new Tile(27, 7));
        tileMap.put("octopus", new Tile(25, 8));
        tileMap.put("ghost", new Tile(14, 31));
        tileMap.put("sword", new Tile(1, 30));
        tileMap.put("fog", new Tile(0, 0));
        tileMap.put("torch", new Tile(11, 25));
        tileMap.put("fish", new Tile(17, 29));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
