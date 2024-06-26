import React from 'react';
import { Link } from 'react-router-dom';
import './loginPanel.css'; // Importujemy plik CSS dla stylizacji

function RegisterPage() {
    return (
        <>
            <div className="main-container"> {/* Zastosowano nową klasę main-container */}
                {/* <div className="header">
                        <p>Gra Terenowa</p>
                    </div> */}
                <div className="signInForm">
                    <h3>Welcome to Sales Campaigns Manager!</h3> {/* Usunięto komponent Typography */}
                    <h5>Register to continue</h5>
                    <form className="form-container"> {/* Zastosowano nową klasę form-container */}
                        <input
                            className="text-input"
                            type="text"
                            placeholder="Username"
                            autoFocus
                        />
                        <input
                            className="text-input"
                            type="password"
                            placeholder="Password"
                        />
                        <input
                            className="text-input"
                            type="password"
                            placeholder="Confirm password"
                        />
                        <button className="submit-btn" type="submit">Register</button> {/* Zmieniono na button zamiast Button */}
                        <div className="another-option-btn">
                            Already have an account?&nbsp; 
                            <Link to="/login">Sign in!</Link> {/* Dodano link do logowania */}
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}

export default RegisterPage;
