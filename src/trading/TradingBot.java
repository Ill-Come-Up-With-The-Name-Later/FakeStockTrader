package trading;

import utilities.Asset;

/**
 * An automatic bot that trades shares of stocks.
 * Has a portfolio and money.
 *
 * @param <T> The type of the asset the bot is trading
 */
public class TradingBot<T extends Asset> {

    private Portfolio<T> portfolio;
    private final double startingCash;
    private double currentCash;

    public TradingBot() {
        this.portfolio = new Portfolio<>();
        this.startingCash = 0;
        this.currentCash = this.startingCash;
    }

    public TradingBot(double startingCash) {
        this.startingCash = startingCash;
        this.currentCash = this.startingCash;
    }

    public TradingBot(double startingCash, Portfolio<T> portfolio) {
        this.startingCash = startingCash;
        this.currentCash = this.startingCash;
        this.portfolio = portfolio;
    }

    /**
     * The bot's portfolio
     *
     * @return The bot's portfolio
     */
    public Portfolio<T> getPortfolio() {
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
        // TODO: ADD ASSET TO PORTFOLIO
    }

    /**
     * Sells an asset
     *
     * @param asset The asset to sell
     */
    public void sellAsset(Asset asset) {
        this.currentCash += asset.getPrice();
        // TODO: REMOVE ASSET FROM PORTFOLIO
    }

    /**
     * The bot's profit
     *
     * @return The amount of profit made by the bot
     */
    public double profit() {
        return this.currentCash - this.startingCash;
    }

    /**
     * If the bot profited
     *
     * @return If the bot profited
     */
    public boolean profited() {
        return this.profit() > 0;
    }
}
