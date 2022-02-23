package com.gildedrose;

/**
 * GildedRose --- class to update the quality of an item
 * @author   Daguinson Fleurantin
 */
class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * update the quality of an item based on different category:
     *
     */
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if(isItemDegrade(items[i])) {
                determineItemQuality(items[i], getConjuredItemDegradeQuality(items[i]));
            }
            if(items[i].name.equals("Aged Brie")) {
                int determinant = isItemExpired(items[i]) ? 2 : 1;
                determineItemQuality(items[i], determinant);
            }
            if(items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                determineBackPassItemQuality(items[i]);
            }
            // decrement sellIn if it's anything other than Sulfurus
            if(!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }
        }
    }

    /**
     * checks if an item is expired
     * @param item The object that is being examined
     * @return true
     */
    private Boolean isItemExpired(Item item) {
        return item.sellIn < 1;
    }

    /**
     *  This method checks the degrading quality of a "conjured" item,
     *  A conjured item degrades twice as fast as normal items
     * @param item The object that is being examined for degradation
     * @return an integer for quality
     */
    private int getConjuredItemDegradeQuality(Item item) {
        int degradeQuality = item.name.equals("Conjured") ? -2 : -1;
        return isItemExpired(item) ? degradeQuality * 2 : degradeQuality;
    }


    private Boolean isItemDegrade(Item item) {
        return !item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")
            && !item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    /**
     * This method determines the quality of an item if it's between 0 and 50
     * @param item, the item (object) that we are trying to adjust the quality for
     * @param determinant  the number to adjust it to
     */
    private void determineItemQuality(Item item, int determinant) {
        int newItemQuality = item.quality + determinant;
        if (newItemQuality <= 50 && newItemQuality >= 0) {
            item.quality = newItemQuality;
        }
    }

    /**
     * This method determines the quality of a backstage pass
     * @param item  the item (object) that we are trying to adjust the quality for
     *              - based on business logic
     */
    private void determineBackPassItemQuality(Item item) {
        determineItemQuality(item, 1);
        if(item.sellIn < 11) {
            determineItemQuality(item, 1);
        }
        if(item.sellIn < 6) {
            determineItemQuality(item, 1);
        }
        if (isItemExpired(item)) {
            item.quality = item.quality - item.quality;
        }
    }

}
