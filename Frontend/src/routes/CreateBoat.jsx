import { useEffect, useState } from "react";
import { render } from "react-dom";
import { Link } from "react-router-dom";
import facade from "../apiFacade";

function CreateBoat({ create }) {
    const init = { brand: "", make: "", boatName: "", boatImage: "" }
    //{ value: "" }
    //{ brand: "", make: "", boatName: "", boatImage: "" }
    const [formData, setFormData] = useState(init);

    const submitFormData = (evt) => {
        evt.preventDefault();
        create(formData.brand, formData.make, formData.boatName, formData.boatImage);
        //event(fromData.value);
        //event(formData.brand, formData.make, formData.boatName, formData.boatImage);
    }
    const onChange = (evt) => {
        setFormData({ ...formData, [evt.target.id]: evt.target.value })
    }

    /*

    event.preventDefault();

    const data = new FormData(event.target);

    const value = Object.fromEntries(data.entries());

    facade.postData("createboat", value)

    console.log({ value });

    */
    //}

    //const CreateBoat = () => {
    return (
        <div>
            <h2>Create Boat</h2>
            <form onChange={onChange}>
                <input placeholder="Brand" id="brand" />
                <input placeholder="Make" id="make" />
                <input placeholder="Name" id="boatName" />
                <input placeholder="Image" id="boatImage" />
                <button onClick={submitFormData}>Create</button>
            </form>
        </div>
    )
}

export default CreateBoat;