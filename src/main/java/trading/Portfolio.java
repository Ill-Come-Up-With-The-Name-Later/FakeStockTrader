package trading;

import utilities.Asset;

import java.util.ArrayList;

/**
 * A set of traded assets and the
 * number of shares held of each.
 */
public class Portfolio {

    private final ArrayList<Asset> assets;

    public Portfolio() {
        assets = new ArrayList<>();
    }

    /**
     * An <code>ArrayList</code> of assets held
     * by this portfolio
     *
     * @return An <code>ArrayList</code> of assets held
     *         by this portfolio
     */
    public ArrayList<Asset> getAssets() {
        return assets;
    }

    /**
     * The value of this portfolio
     *
     * @return The value of this portfolio
     */
    public double value() {
        double val = 0;

        for(Asset asset : assets) {
            val += asset.calculatePrice();
        }

        return val;
    }

    /**
     * Adds an <code>Asset</code> to this portfolio
     *
     * @param asset The asset
     */
    public void addAsset(Asset asset) {
        this.assets.add(asset);
    }

    /**
     * Removes an <code>Asset</code> from this portfolio
     *
     * @param asset The asset
     */
    public void removeAsset(Asset asset) {
        this.assets.remove(asset);
    }
}
