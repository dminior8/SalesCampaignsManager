import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import Cookies from "js-cookie";
import "./appContent.css";

const CampaignsPage = () => {
  const [main, setMain] = useState([]);
  const [user, setUser] = useState({ id: '', username: '', balance: 0 }); // Nowy stan dla danych użytkownika
  const { productId } = useParams(); // Pobieranie productId ze ścieżki URL

  const fetchData = async () => {
    try {
      const token = Cookies.get("accessTokenFront"); // Pobierz token z ciasteczka
      console.log("Access Token:", token); // Sprawdź, czy token jest prawidłowo pobierany

      if (!token) {
        console.error("No token, can't get data.");
        return;
      }

      const userResponse = await axios.get(
        `http://localhost:8090/api/user/current`, // Zaktualizowana ścieżka API
        {
          headers: {
            Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
          },
        }
      );
      console.log("User API Response:", userResponse.data);

      const userData = {
        id: userResponse.data.id,
        username: userResponse.data.username,
        balance: userResponse.data.balance
      };
   
      const mainResponse = await axios.get(
        `http://localhost:8090/api/campaigns/products`,
        {
          headers: {
            Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
          },
        }
      );
      console.log("Product ID:", productId);
      console.log("API Response:", mainResponse.data);

      const mainData = mainResponse.data.map((data) => ({
        productId: data.id,
        name: data.name,
        description: data.description, 
        price: data.price
      }));

      setMain(mainData);
      setUser(userData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [productId]);

  return (
    <div className="AppContent">
      <div className="container-fluid">
        <div className="d-flex flex-column align-items-center">
          <div className="col-md-4">
            <div className="py-3">
              <table className="table border shadow">
                <thead>
                  <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Balance</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <i>
                        <b>{user.username}</b>
                      </i>
                    </td>
                    <td>{user.balance}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div className="campaign-list">
          <div className="col-md-12">
            <div className="py-2">
              <table className="table border shadow">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description amount</th>
                    <th scope="col">Price</th>
                    <th scope="col">Options</th>
                  </tr>
                </thead>
                <tbody>
                  {main.map((data, index) => (
                    <tr key={index}>
                      <th scope="row">{index + 1}</th>
                      <td>
                        <i>
                          <b>{data.name}</b>
                        </i>
                      </td>
                      <td>{data.description}</td>
                      <td>{data.price}</td>
                      <td>
                        <Link
                          className="btn btn-outline-primary mx-1"
                          to={`/${data.productId}`}
                        >
                          See more
                        </Link>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CampaignsPage;
