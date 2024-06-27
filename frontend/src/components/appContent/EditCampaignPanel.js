import React, { useState, useEffect } from "react";
import Select from "react-select";
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import axios from "axios";
import { useParams } from "react-router-dom";
import Cookies from "js-cookie";
import "./appContent.css";

const EditCampaignPage = () => {
  const [user, setUser] = useState({ id: '', username: '', balance: '' });
  const [keywords, setKeywords] = useState([]);
  const [cities, setCities] = useState([]);
  const [campaign, setCampaign] = useState({
    id: '',
    name: '',
    keywords: [],
    product: {
      id: '',
      name: ''
    },
    bidAmount: '',
    fund: '',
    status: 'ON',
    city: {
      id: '',
      name: ''
    },
    radius: '',
    accountId: ''
  });
  const { campaignId } = useParams();
  const [showModal, setShowModal] = useState(false);
  const [initialFunds, setInitialFunds] = useState(0);

  const fetchData = async () => {
    try {
      const token = Cookies.get("accessTokenFront"); 
      console.log("Access Token:", token); 

      if (!token) {
        console.error("No token, can't get data.");
        return;
      }

      const userResponse = await axios.get(
        `http://localhost:8090/api/user/current`, 
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
      console.log("User API Response:", userResponse.data);
      const userData = {
        id: userResponse.data.id,
        username: userResponse.data.username,
        balance: userResponse.data.balance
      };
      setUser(userData);

      const citiesResponse = await axios.get(
        `http://localhost:8090/api/campaigns/cities`, 
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
      console.log("Cities API Response:", citiesResponse.data);
      const citiesData = citiesResponse.data.map((city) => ({
        value: city.id,
        label: city.name,
      }));
      setCities(citiesData);

      const keywordsResponse = await axios.get(
        `http://localhost:8090/api/campaigns/keywords`, 
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
      console.log("Keywords API Response:", keywordsResponse.data);
      const keywordsData = keywordsResponse.data.map((keyword) => ({
        value: keyword.id,
        label: keyword.name,
      }));
      setKeywords(keywordsData);

      const campaignResponse = await axios.get(
        `http://localhost:8090/api/campaigns/${campaignId}`, 
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
      console.log("Campaign API Response:", campaignResponse.data);

      const campaignData = {
        id: campaignId,
        name: campaignResponse.data.name,
        keywords: campaignResponse.data.keywords.map(keyword => ({
          value: keyword.id,
          label: keyword.name
        })),
        bidAmount: campaignResponse.data.bidAmount,
        fund: campaignResponse.data.fund,
        status: campaignResponse.data.status,
        city: campaignResponse.data.city,
        radius: campaignResponse.data.radius,
        accountId: userResponse.data.id
      };
      setCampaign(campaignData);
      console.log(campaignData);
      setInitialFunds(campaignResponse.data.fund);

    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [campaignId]);

  useEffect(() => {
    setCampaign((prevCampaign) => ({
      ...prevCampaign,
      accountId: user.id
    }));
  }, [user.id]);

  const onInputChange = (e) => {
    setCampaign({ ...campaign, [e.target.name]: e.target.value });
  };

  const onKeywordsChange = (selectedOptions) => {
    setCampaign({
      ...campaign,
      keywords: selectedOptions
    });
  };

  const onCityChange = (selectedOption) => {
    setCampaign({
      ...campaign,
      city: selectedOption
    });
  };

  const handleStatusChange = () => {
    const newStatus = campaign.status === 'ON' ? 'OFF' : 'ON'; 
    setCampaign({ ...campaign, status: newStatus });
  };

  const validateFunds = () => {
    if (parseFloat(campaign.fund) < parseFloat(initialFunds)) {
      console.error("Fund cannot be less than previous fund.");
      return false;
    } else if (parseFloat(campaign.fund) - parseFloat(initialFunds) > parseFloat(user.balance)) {
      console.error("Added fund cannot be greater than balance.");
      return false;
    } else {
      return true;
    }
  };  

  const onSubmit = async (e) => {
    e.preventDefault();
    
    if (!validateFunds()) {
      console.error("Insufficient funds.");
      alert("Wrong value, please try again.");
      return;
    }
    
    try {
      const token = Cookies.get("accessTokenFront");
  
      if (!token) {
        console.error("No token, can't send data.");
        return;
      }
  
      // Aktualizacja balansu użytkownika
      const newBalance = initialFunds + user.balance - campaign.fund;
      const userData = {
        username: user.username,
        balance: newBalance
      };
      setUser(userData);
  
      await axios.put(
        `http://localhost:8090/api/campaigns/${campaignId}/edit`,
        {
          ...campaign,
          keywords: campaign.keywords.map(keyword => ({
            id: keyword.value,
            name: keyword.label
          })),
          city: {
            id: campaign.city.id,
            name: campaign.city.name
          }
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      ;
  
      // Ponowne pobranie danych po zapisaniu kampanii
      fetchData();

      // Pokaż okno modalne po pomyślnym zakończeniu żądania PUT
      setShowModal(true);
  
    } catch (error) {
      console.error("Error submitting data:", error);
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
        <div className="campaign-list shadow">
          <div className="col-md-12">
            <div className="py-2">
              <h2 className="text-center mb-4">Edit campaign</h2>
              
              <form onSubmit={onSubmit}>
                <div className="col-md-4 mx-auto">
                  <label htmlFor="Name" className="form-label">
                    Name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Enter Name of campaign"
                    name="name"
                    required
                    value={campaign.name}
                    onChange={(e) => onInputChange(e)}
                  />
                </div>

                <div className="col-md-4 mx-auto">
                  <label htmlFor="keywords">Keywords</label>
                  <Select
                    id="keywords"
                    name="keywords"
                    options={keywords}
                    onChange={onKeywordsChange}
                    value={campaign.keywords}
                    isMulti
                    required
                    styles={{
                      control: (provided) => ({ ...provided, textAlign: "left" }),
                    }}
                  />
                </div>

                <div className="col-md-4 mx-auto">
                  <label htmlFor="city">City</label>
                  <Select
                    id="city"
                    name="city"
                    options={cities}
                    onChange={onCityChange}
                    value={cities.find(option => option.label === campaign.city.name)}
                    required
                    styles={{
                      control: (provided) => ({ ...provided, textAlign: "left" }),
                    }}
                  />
                </div>

                <div className="container col-md-4 order-md-2">
                  <div className="row">
                    <div className="col-md-6 order-md-2">
                      <div className="row">
                        <div className="col-md-12">
                          <label htmlFor="radius">Radius (km)</label>
                          <input
                            id="radius"
                            name="radius"
                            type="number"
                            className="form-control"
                            value={campaign.radius}
                            onChange={onInputChange}
                            placeholder="Enter radius"
                          />
                        </div>
                      </div>
                    </div>

                    <div className="col-md-6 order-md-1 d-flex justify-content-center align-items-center">
                      <div className="row">
                        <div className="col-md-12">
                          <Form.Check
                            type="switch"
                            id="custom-switch"
                            checked={campaign.status === "ON"}
                            onChange={handleStatusChange}
                            label="Is campaign active?"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-md-4 mx-auto">
                  <label htmlFor="bidAmount">Bid Amount (PLN)</label>
                  <input
                    id="bidAmount"
                    name="bidAmount"
                    type="number"
                    value={campaign.bidAmount}
                    className={`form-control ${campaign.bidAmount >= 1 ? "" : "is-invalid"}`}
                    onChange={onInputChange}
                    placeholder="Enter bid amount"
                    step="0.5"
                    min="1"
                    required
                  />
                </div>
                <div className="col-md-4 mx-auto">
                  <label htmlFor="fund">Funds (PLN)</label>
                  <input
                    id="fund"
                    name="fund"
                    type="number"
                    className={`form-control ${campaign.fund > user.balance ? "is-invalid" : ""}`}
                    value={campaign.fund}
                    onChange={onInputChange}
                    placeholder="Enter fund amount"
                    min="0"
                    required
                  />
                </div>
                <div className="mt-4"></div>
                <div className="text-center">
                  <button type="submit" className="btn btn-primary">
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Success</Modal.Title>
        </Modal.Header>
        <Modal.Body>Campaign updated successfully!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default EditCampaignPage;
