import './style.css';

function Button() {
    return <button className="fraudButton">fraud button</button>;
}

function App() {
    return (
        <>
            <h1>Test</h1>
            <p>
                This webpage is for a fake stock trading bot.
                The bot may eventually be usable, but for now, 
                so that we don't get accused of fraud, I'm going
                to be transparent and say the truth: The bot does not exist.
            </p>
            <Button/>
        </>
    );
}

export default App;
