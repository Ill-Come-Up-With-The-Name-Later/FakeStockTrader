package trading.assets;

import utilities.Asset;

/**
 * A stock traded on the open market.
 * Has a price per share.
 */
public class Stock extends Asset {

    @Override
    public double calculatePrice() {
        return this.getPrice() * this.getAmount();
    }
}
