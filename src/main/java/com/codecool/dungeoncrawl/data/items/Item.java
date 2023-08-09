package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private final String name;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.name = name;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
    public String getName() {
        return name;
    }

    @Override
    public String getTileName() {
        return name;
    }
}
