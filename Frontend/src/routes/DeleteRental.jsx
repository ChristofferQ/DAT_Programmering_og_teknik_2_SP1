import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import facade from "../apiFacade";

const DeleteRental = () => {
    const [rentalList, setRentalList] = useState([]);
    useEffect(() => {
        facade.fetchData("rentals")
            .then(data => {
                setRentalList(data);
            })
    }, []);

    function deleteRental(id) {
        let conFirmmAction = confirm(`Are you sure you want to delete rental ${id}?`)
        if (conFirmmAction) {
            facade.deleteData(`rental/delete/${id}`)
                .then(data => {
                    setRentalList(data);
                    alert(`Deleted rental ${id}`)
                })
        } else {
            alert("Action cancelled")
        }
    }

    const [query, setQuery] = useState("");

    return (

        //<main style={{ padding: "1rem 0" }}>

        <div>
            <h2>Rentals</h2>
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
                            <tr onClick={() => deleteRental(Rental.id)}>
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
        </div>
    )
}

export default DeleteRental;