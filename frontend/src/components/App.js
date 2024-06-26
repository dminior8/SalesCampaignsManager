import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import "./App.css";
import Footer from "./footer/Footer";
import Header from "./header/Header";
import CampaignsPage from "./appContent/CamapignsPanel";
import AddCampaignPage from "./appContent/AddCamapaignPanel";
import LoginPanel from "./loginPanel/LoginPanel";
import RegisterPage from "./loginPanel/RegisterPanel";
import ProductsPage from "./appContent/ProductsPanel";
import Cookies from "js-cookie";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(!!Cookies.get("accessTokenFront")); 

  useEffect(() => {
    setIsLoggedIn(!!Cookies.get("accessTokenFront"));
  }, []);

  const renderLoginPage = () => {
    return isLoggedIn ? (
      <Navigate to="/api/campaigns/products" />
    ) : (
      <LoginPanel onLogin={setIsLoggedIn} />
    );
  };

  const renderRegisterPage = () => {
    return isLoggedIn ? <Navigate to="/api/products" /> : <RegisterPage />;
  };

  const renderCampaignsPage = () => {
    return isLoggedIn ? <CampaignsPage /> : <Navigate to="/api/auth/login" />;
  };

  const renderCampaignsAddPage = () => {
    return isLoggedIn ? <AddCampaignPage /> : <Navigate to="/api/auth/login" />;
  };

  const renderProductsPage = () => {
    return isLoggedIn ? <ProductsPage /> : <Navigate to="/api/auth/login" />;
  };

  return (
    <Router>
      <div className="App">
        {isLoggedIn && <Header className="header" />}
        <Routes>
          <Route path="/api/auth/login" element={renderLoginPage()} />
          <Route path="/api/auth/register" element={renderRegisterPage()} />
          <Route path="/api/campaigns/products/:productId" element={renderCampaignsPage()} />
          <Route path="/api/campaigns/products" element={renderProductsPage()} />
          <Route path="/api/campaigns" element={renderCampaignsPage()} />
          <Route path="/api/campaigns/products/:productId/add" element={renderCampaignsAddPage()} />
          <Route path="/api/logout" element={renderCampaignsPage()} />
          <Route
            path="*"
            element={
              isLoggedIn ? (
                <Navigate to="/api/campaigns/products" />
              ) : (
                <Navigate to="/api/auth/login" />
              )
            }
          />
        </Routes>
        <Footer className="footer" />
      </div>
    </Router>
  );
}

export default App;
