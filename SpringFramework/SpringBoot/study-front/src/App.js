import './App.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Signin from './components/user/Sign_in';

function App() {
  return (
    <div className="all-container">
      <Router>
        <Routes>
          <Route exact path="/" element={Signin()} />
        </Routes>
      </Router>

    </div>
  );
}

export default App;
