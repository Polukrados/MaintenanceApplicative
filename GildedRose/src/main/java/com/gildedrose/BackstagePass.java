package com.gildedrose;

class BackstagePass extends AbstractItem {

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        increaseQuality();
        if (item.sellIn < 11) {
            increaseQuality();
        }
        if (item.sellIn < 6) {
            increaseQuality();
        }
        decreaseSellIn();
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
