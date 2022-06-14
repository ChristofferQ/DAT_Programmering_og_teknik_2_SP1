import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import facade from "../apiFacade";

const Tenant = () => {
    const [tenantList, setTenantList] = useState([]);
    useEffect(() => {
        facade.fetchData("tenants")
            .then(data => {
                setTenantList(data);
            })
    }, []);

    const [query, setQuery] = useState("");

    return (

        //<main style={{ padding: "1rem 0" }}>

        <div>
            <h2>Tenants</h2>
            <table className="table">
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
    )
}

export default Tenant;