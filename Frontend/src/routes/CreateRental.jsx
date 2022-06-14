import { useEffect, useState } from "react";
import { render } from "react-dom";
import { Link } from "react-router-dom";
import facade from "../apiFacade";

function CreateRental() {

    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [priceAnnual, setPriceAnnual] = useState('');
    const [deposit, setDeposit] = useState('');
    const [contactPerson, setContactPerson] = useState('');

    const handleStartDate = evt => {
        setStartDate(evt.target.value);
    }
    const handleEndDate = evt => {
        setEndDate(evt.target.value);
    }
    const handlePriceAnnual = evt => {
        setPriceAnnual(evt.target.value);
    }
    const handleDeposit = evt => {
        setDeposit(evt.target.value);
    }
    const handleContactPerson = evt => {
        setContactPerson(evt.target.value);
    }

    const handleSubmit = evt => {
        evt.preventDefault();
        facade.createRental(startDate, endDate, priceAnnual, deposit, contactPerson)
    }

    return (
        <div>
            <h2>Create Rental</h2>
            <form onSubmit={handleSubmit}>
                <input placeholder="StartDate" id="startDate" onChange={handleStartDate} />
                <input placeholder="EndDate" id="endDate" onChange={handleEndDate} />
                <input placeholder="PriceAnnual" id="priceAnnual" onChange={handlePriceAnnual} />
                <input placeholder="Deposit" id="deposit" onChange={handleDeposit} />
                <input placeholder="ContactPerson" id="contactPerson" onChange={handleContactPerson} />
                <input type="submit" value="Submit" />
            </form>
        </div>
    )
}

export default CreateRental;