import { useState } from 'react'
//import reactLogo from './assets/react.svg'
//import viteLogo from '/vite.svg'
import './App.css'
import { Outlet, useNavigate } from 'react-router';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const navigate = useNavigate();

  const handleSignout = () => {
    localStorage.clear();
    setLoggedIn(false);
    navigate("/login");
  };

  const loginButton = loggedIn ? <button className="btn btn-primary" onClick={handleSignout}>Logout</button> : '';

  return (
    <>
      <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark justify-content-around">
        <h1 className="navbar-brand">Employee Reimbursement System</h1>
        {loginButton}
      </nav>
      <Outlet context={{ setLoggedIn }} />
    </>
  )
}

export default App;