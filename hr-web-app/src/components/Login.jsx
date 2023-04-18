import { Link } from "react-router-dom";
import '../App.css';

export const Login = () =>{
    return (
        <div className="login-container">
    <p> Welcome to HR platform</p>
      <Link to="/homepage">
        <button className="login-button" >
         LOGIN
        </button>
      </Link>
    </div>
    )
}