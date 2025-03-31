package utilities;

import java.util.ArrayList;

/**
 * An asset with monetary value, be it a crypto coin or a
 * stock
 */
public abstract class Asset {

    // TODO: Add more generic properties that apply to other assets
    private double price;
    private final ArrayList<Double> previousPrices = new ArrayList<>();

    /**
     * Price of the asset
     *
     * @return The price of this asset
     */
    public double getPrice() {
        return price;
    }

    /**
     * An <code>ArrayList</code> of previous
     * prices of the asset
     *
     * @return Previous prices of the asset in an <code>ArrayList</code>
     */
    public ArrayList<Double> getPreviousPrices() {
        return previousPrices;
    }
}
