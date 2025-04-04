package trading;

import utilities.Asset;

import java.util.HashMap;

/**
 * An automatic bot that trades shares of stocks.
 * Has a portfolio and money.
 */
public class TradingBot {

    private Portfolio portfolio;
    private final double startingCash;
    private double currentCash;
    private final HashMap<Asset, Double> initialAssetBuyPrice;

    public TradingBot() {
        this.portfolio = new Portfolio();
        this.startingCash = 0;
        this.currentCash = this.startingCash;
        this.initialAssetBuyPrice = new HashMap<>();
    }

    public TradingBot(double startingCash) {
        this.startingCash = startingCash;
        this.currentCash = this.startingCash;
        this.initialAssetBuyPrice = new HashMap<>();
    }

    public TradingBot(double startingCash, Portfolio portfolio) {
        this.startingCash = startingCash;
        this.currentCash = this.startingCash;
        this.portfolio = portfolio;
        this.initialAssetBuyPrice = new HashMap<>();
    }

    /**
     * The bot's portfolio
     *
     * @return The bot's portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * The bot's starting cash
     *
     * @return The bot's starting cash
     */
    public double getStartingCash() {
        return startingCash;
    }

    /**
     * The bot's current cash
     *
     * @return The bot's current cash
     */
    public double getCurrentCash() {
        return currentCash;
    }

    /**
     * Set the bot's current cash
     *
     * @param currentCash The amount of money
     */
    public void setCurrentCash(double currentCash) {
        this.currentCash = currentCash;
    }

    /**
     * Buys an asset
     *
     * @param asset The asset to buy
     */
    public void buyAsset(Asset asset) {
        if(this.currentCash < asset.getPrice()) {
            throw new RuntimeException("Insufficient funds!");
        }

        this.currentCash -= asset.getPrice();
        this.portfolio.addAsset(asset);
        this.initialAssetBuyPrice.put(asset, asset.getPrice());
    }

    /**
     * Sells an asset
     *
     * @param asset The asset to sell
     */
    public void sellAsset(Asset asset) {
        this.currentCash += asset.getPrice();
        this.portfolio.removeAsset(asset);
    }

    /**
     * The bot's profit
     *
     * @return The amount of profit made by the bot
     */
    public double profit() {
        return this.netWorth() - this.startingCash;
    }

    /**
     * If the bot profited
     *
     * @return If the bot profited
     */
    public boolean profited() {
        return this.profit() > 0;
    }

    /**
     * The total cash this bot has. Inclusive of
     * portfolio value and current cash on hand
     *
     * @return The bot's net worth
     */
    public double netWorth() {
        return this.portfolio.value() + this.currentCash;
    }

    /**
     * The price the asset was bought for by the bot
     *
     * @param asset The asset
     * @return The initial price paid for the asset
     */
    public double getInitialBuyPrice(Asset asset) {
        return initialAssetBuyPrice.get(asset);
    }
}
