import './App.css';
import React from "react";
import { Routes, Route, Router } from "react-router-dom";
import { Homepage } from './components/Homepage';
import { Login } from './components/Login';



function App() {
  return (
    <div className="App">
      <header className="App-header">
     
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/homepage" element={<Homepage />} />
      </Routes> </header>
    </div>
  );
}

export default App;
