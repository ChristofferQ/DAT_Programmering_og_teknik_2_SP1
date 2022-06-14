import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import facade from "../apiFacade";

const House = () => {
  const [houseList, setHouseList] = useState([]);
  const [tenantList, setTenantList] = useState([]);
  useEffect(() => {
    facade.fetchData("houses")
      .then(data => {
        setHouseList(data);
      })
  }, []);

  function tenantsByHouseId(id) {
    facade.fetchData(`tenant/rental/${id}`)
      .then(data => {
        setTenantList(data);
      })
  };

  const [query, setQuery] = useState("");

  return (

    <div>
      <h2>House</h2>
      <h3>Select a house to see to see all tenants currently living there:</h3>
      <table className="table-hover">
        <thead>
          <tr>
            <th>Id</th>
            <th>Address</th>
            <th>City</th>
            <th>Number of Rooms</th>
          </tr>
        </thead>
        <tbody>
          {
            houseList.map((House) => (
              <tr onClick={() => tenantsByHouseId(House.id)}>
                <td>{House.id}</td>
                <td>{House.address}</td>
                <td>{House.city}</td>
                <td>{House.numberOfRooms}</td>
              </tr>
            ))
          }
        </tbody>
      </table>
      <div>
        <h3>Tenants </h3>
        <table className="table-hover">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Phone</th>
              <th>Job</th>
            </tr>
          </thead>
          <tbody>
            {
              tenantList.map((Tenant) => (
                <tr>
                  <td>{Tenant.id}</td>
                  <td>{Tenant.name}</td>
                  <td>{Tenant.phone}</td>
                  <td>{Tenant.job}</td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default House;
