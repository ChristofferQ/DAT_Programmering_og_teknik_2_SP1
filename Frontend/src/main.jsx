import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import App from './App'
import './styles/index.css'
import Rental from './routes/Rentals'
import House from './routes/Houses'
import Tenant from './routes/Tenants'
import CreateRental from './routes/CreateRental'
import ConnectBoat from './routes/ConnectBoat'
import EditRental from './routes/EditRental'
import DeleteRental from './routes/DeleteRental'

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} >
        <Route path="rental" element={<Rental />} />
        <Route path="house" element={<House />} />
        <Route path="tenant" element={<Tenant />} />
        <Route path="createrental" element={<CreateRental />} />
        <Route path="connectboat" element={<ConnectBoat />} />
        <Route path="editrental" element={<EditRental />} />
        <Route path="deleterental" element={<DeleteRental />} />
        <Route
          path="*"
          element={
            <main style={{ padding: "1rem" }}>
              <p>There's nothing here!</p>
            </main>
          }
        />
      </Route>
    </Routes>
  </BrowserRouter>,
);
