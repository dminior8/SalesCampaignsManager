import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import Cookies from "js-cookie";
import "./appContent.css";

const CampaignsPage = () => {
  const [main, setMain] = useState([]);
  const [user, setUser] = useState({ username: '', balance: 0 }); // Nowy stan dla danych użytkownika
  const { productId } = useParams(); // Pobieranie productId ze ścieżki URL

  const fetchData = async () => {
    try {
      const token = Cookies.get("accessTokenFront"); // Pobierz token z ciasteczka
      console.log("Access Token:", token); // Sprawdź, czy token jest prawidłowo pobierany

      if (!token) {
        console.error("No token, can't get data.");
        return;
      }

      const mainResponse = await axios.get(
        `http://localhost:8090/api/campaigns`,
        {
          headers: {
            Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
          },
        }
      );
      console.log("API Response:", mainResponse.data);

      const userResponse = await axios.get(
        `http://localhost:8090/api/user/current`, // Zaktualizowana ścieżka API
        {
          headers: {
            Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
          },
        }
      );
      console.log("User API Response:", userResponse.data);

      const mainData = mainResponse.data.map((data) => ({
        campaignId: data.id,
        name: data.name,
        keywords: data.keywords.join(", "), // Łączenie tablicy słów kluczowych w pojedynczy string
        bidAmount: data.bidAmount,
        fund: data.fund,
        status: data.status,
        city: data.city,
        radius: data.radius,
        createdAt: data.createdAt
      }));

      const userData = {
        username: userResponse.data.username,
        balance: userResponse.data.balance
      };

      setMain(mainData);
      setUser(userData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [productId]);

  const deleteCampaign = async (id) => {
    try {
      const token = Cookies.get("accessTokenFront");
      console.log("Access Token:", token);

      if (!token) {
        console.error("No token, can't get data.");
        return;
      }

      await axios.delete(`http://localhost:8090/api/campaigns/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Przekazanie tokena w nagłówku
        },
      });

      fetchData(); // Odśwież listę po usunięciu kampanii
      console.log("Delete campaign with id:", id);
    } catch (error) {
      console.error("Error deleting campaign:", error);
    }
  };

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
                    <th scope="col">Bid amount</th>
                    <th scope="col">Fund</th>
                    <th scope="col">Status</th>
                    <th scope="col">City</th>
                    <th scope="col">Radius</th>
                    <th scope="col">Keywords</th>
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
                      <td>{data.bidAmount}$</td>
                      <td>{data.fund}$</td>
                      <td>
                        <i>{data.status}</i>
                      </td>
                      <td>{data.city}</td>
                      <td>{data.radius} km</td>
                      <td>{data.keywords}</td>
                      <td>
                        <Link
                          className="btn btn-outline-primary mx-1"
                          to={`/${data.campaignId}`}
                        >
                          Edit
                        </Link>
                        <button
                          className="btn btn-danger mx-1"
                          onClick={() => deleteCampaign(data.campaignId)}
                        >
                          Delete
                        </button>
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
