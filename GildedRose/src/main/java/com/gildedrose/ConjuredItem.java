package com.gildedrose;

class ConjuredItem extends AbstractItem {

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        decreaseQuality();
        decreaseQuality(); // Se dégrade 2x plus vite
        decreaseSellIn();
        if (item.sellIn < 0) {
            decreaseQuality();
            decreaseQuality();
        }
    }
}
