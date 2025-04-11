import './style.css';
import Button from './TestButton';
import Intro from './IntroParagraph';

// Keep just the main App() function in this file.
// Minimize code here and keep most of the elements as
// separate React components.

function App() {
    return (
        <>
            <h1 id="pageTitle" class="font-bold">Test</h1>
            <Intro/>
            <Button/>
        </>
    );
}

export default App;
