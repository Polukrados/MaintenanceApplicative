package com.gildedrose;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private void executeUpdateQualityTest(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedSellIn, app.items[0].sellIn, "SellIn incorrect for " + name);
        assertEquals(expectedQuality, app.items[0].quality, "Quality incorrect for " + name);
    }

    @Test
    void testNormalItemDegradesCorrectly() {
        executeUpdateQualityTest("Normal Item", 0, 20, -1, 18);
        executeUpdateQualityTest("Normal Item", 0, -1, -1, -1);
    }

    @Test
    void testAgedBrieIncreasesQualityOverTime() {
        executeUpdateQualityTest("Aged Brie", 0, 20, -1, 22);
        executeUpdateQualityTest("Aged Brie", 0, 50, -1, 50);

    }

    @Test
    void testBackstagePassesQualityChanges() {
        executeUpdateQualityTest("Backstage passes to a TAFKAL80ETC concert", 15, 20, 14, 21);
        executeUpdateQualityTest("Backstage passes to a TAFKAL80ETC concert", 0, 20, -1, 0);
        executeUpdateQualityTest("Backstage passes to a TAFKAL80ETC concert", 5, 49, 4, 50);
    }

    @Test
    void testSulfuras() {
        executeUpdateQualityTest("Sulfuras, Hand of Ragnaros", -1, 1, -1, 1);
    }
}
