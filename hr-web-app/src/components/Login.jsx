import { Link } from "react-router-dom";
import '../App.css';
import { useState } from "react";


const logIn = () =>{
  localStorage.setItem('loggedIn', true);

}

export const Login = () =>{
    return (
        <div className="login-container">
    <p> Welcome to HR platform</p>
      <Link to="/homepage">
        <button className="login-button" onClick={logIn()} >
         LOGIN
        </button>
      </Link>
    </div>
    )
}