package utilities;

import java.util.ArrayList;

/**
 * An asset with monetary value, be it a crypto coin or a
 * stock
 */
public abstract class Asset {

    // TODO: Add more generic properties that apply to other trading.assets
    private double price;
    private final ArrayList<Double> previousPrices = new ArrayList<>();
    private Status status;

    /**
     * Price of the asset
     *
     * @return The price of this asset
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the asset's price
     *
     * @param price The new price of this <code>Asset</code>
     */
    public void setPrice(double price) {
        this.price = price;
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

    /**
     * The status of the price of this asset
     *
     * @return This asset's price's status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of this asset's price
     *
     * @param status The status of this asset's price
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
