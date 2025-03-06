package com.gildedrose;

abstract class AbstractItem {
    protected Item item;

    public AbstractItem(Item item) {
        this.item = item;
    }

    public abstract void updateQuality();

    protected void decreaseSellIn() {
        item.sellIn--;
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
