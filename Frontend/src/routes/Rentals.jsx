import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import facade from "../apiFacade";

const Rental = () => {
    const [rentalList, setRentalList] = useState([]);
    const [tenantList, setTenantList] = useState([]);
    useEffect(() => {
        facade.fetchData("rentals")
            .then(data => {
                setRentalList(data);
            })
    }, []);

    function getHouseByRental(id) {
        facade.fetchData(`house/rental/${id}`)
            .then(data => {
                setTenantList(data);
            })
    };

    const [query, setQuery] = useState("");

    return (

        //<main style={{ padding: "1rem 0" }}>

        <div>
            <h2>Rentals</h2>
            <h3>Select a rental to see all details about the house:</h3>
            <table className="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Price Annual</th>
                        <th>Deposit</th>
                        <th>Contact Person</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        rentalList.map((Rental) => (
                            <tr onClick={() => getHouseByRental(Rental.id)}>
                                <td>{Rental.id}</td>
                                <td>{Rental.startDate}</td>
                                <td>{Rental.endDate}</td>
                                <td>{Rental.priceAnnual}</td>
                                <td>{Rental.deposit}</td>
                                <td>{Rental.contactPerson}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
            <div>
                <h3>House </h3>
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
                            tenantList.map((House) => (
                                <tr>
                                    <td>{House.id}</td>
                                    <td>{House.address}</td>
                                    <td>{House.city}</td>
                                    <td>{House.numberOfRooms}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Rental;