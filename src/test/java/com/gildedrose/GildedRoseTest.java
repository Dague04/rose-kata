package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void itemExpired_qualityDecrease() {
        GildedRose app = new GildedRose(new Item[]{new Item("foo", 0, 10)});
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);

    }

    @Test
    public void item_quality_can_never_be_negative() {
        GildedRose app = new GildedRose(new Item[]{new Item("foo", 0, 0)});
        app.updateQuality();

        assertEquals(-1,app.items[0].sellIn);
        assertEquals(0,app.items[0].quality);
   }

   @Test
   public void item_quality_can_never_go_over_50() {
       GildedRose app = new GildedRose(new Item[]{new Item("Aged Brie", 2, 50)});
       app.updateQuality();

       assertEquals(1,app.items[0].sellIn);
       assertEquals(50,app.items[0].quality);
   }

   @Test
    public void backstage_passes_quality_increases_by_2_when_there_are_10_days_or_less() {
       GildedRose app = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",
           10, 5)});
       app.updateQuality();

       assertEquals(9,app.items[0].sellIn);
       assertEquals(7,app.items[0].quality);

    }

    @Test
    public void backstage_passes_quality_increases_by_3_when_there_are_5_days_or_less() {
        GildedRose app = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",
            5, 5)});
        app.updateQuality();

        assertEquals(4,app.items[0].sellIn);
        assertEquals(8,app.items[0].quality);

    }

    @Test
    public void backstage_passes_quality_drops_to_zero_after_concert() {
        GildedRose app = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",
            0, 5)});
        app.updateQuality();

        assertEquals(-1,app.items[0].sellIn);
        assertEquals(0,app.items[0].quality);
    }

   @Test
   public void getConjuredItemDegradeQualityTest() {
       GildedRose app = new GildedRose(new Item[]{new Item("Conjured", 5, 10)});

       app.updateQuality();
       assertEquals(4,app.items[0].sellIn);

       assertEquals(8,app.items[0].quality);
   }

}
