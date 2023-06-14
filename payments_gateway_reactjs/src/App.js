import logo from './logo.svg';
import './assets/main.scss';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <span className="flex w-3 h-3 bg-red-500 rounded-full"></span>

      </header>
    </div>
  );
}

export default App;
