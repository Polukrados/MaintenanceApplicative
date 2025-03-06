package com.gildedrose;

class GildedRose {
    public final AbstractItem[] items;

    public GildedRose(Item[] items) {
        this.items = new AbstractItem[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = createItem(items[i]);
        }
    }

    private AbstractItem createItem(Item item) {
        return switch (item.name) {
            case "Aged Brie" -> new AgedBrie(item);
            case "Backstage passes to a TAFKAL80ETC concert" -> new BackstagePass(item);
            case "Sulfuras, Hand of Ragnaros" -> new Sulfuras(item);
            case "Conjured Mana Cake" -> new ConjuredItem(item);
            default -> new NormalItem(item);
        };
    }

    public void updateQuality() {
        for (AbstractItem item : items) {
            item.updateQuality();
        }
    }

    public Item getItem(int i) {
        return items[i].item;
    }
}
