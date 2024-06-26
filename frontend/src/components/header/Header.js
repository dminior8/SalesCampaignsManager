import React from 'react';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom'; // Import do nawigacji w React Router
import Cookies from "js-cookie";
import './header.css';

function MainNavigationWithAlert() {
  let navigate = useNavigate(); // Hook nawigacji

  const handleLogout = (e) => {
    e.preventDefault();
    const allCookies = Cookies.get();
    for (let cookie in allCookies) {
      Cookies.remove(cookie);
    }
    //onLogin(false);
    navigate("/api/auth/login");
    window.location.reload()
  };

  return (
    <Navbar id="main-navigation-bar">
      <Navbar.Brand href="/api/campaigns/products">Sales Campaigns Manager</Navbar.Brand>

      <Nav activeKey="/">
        <Nav.Item>
          <Nav.Link eventKey="/api/campaigns" href="/api/campaigns">
            Campaigns
          </Nav.Link>
        </Nav.Item>

        <Nav.Item>
          <Nav.Link eventKey="/api/campaigns/add" href="/api/campaigns/add">
            Add new
          </Nav.Link>
        </Nav.Item>
      </Nav>

      <Navbar.Toggle />
      <Navbar.Collapse className="justify-content-end">
        <Nav.Link id="account-element-right" href="/api/auth/login" onClick={handleLogout}>
          Log out
        </Nav.Link>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default MainNavigationWithAlert;
