import './style.css'
import { createRoot } from 'react-dom/client'

const titleRoot = createRoot(document.getElementById("titleNode"))

function PageTitle() {
    return <h1>Hello React</h1>;
}

titleRoot.render(<PageTitle/>)