import "../styles/Homepage.css"
import Navbar from "react-bootstrap/Navbar";
import 'font-awesome/css/font-awesome.min.css';
import { Link } from "react-router-dom";
import { CandidateProfile } from "./CandidateProfile";
import { CardGroup } from "react-bootstrap";
import Form from 'react-bootstrap/Form';
import Select from 'react-select';
import React, { useState } from 'react';

export const Homepage = () => {

    const [selectedOptions, setSelectedOptions] = useState([]);

    //   const options = [
    //     { value: 'apple', label: 'Apple' },
    //     { value: 'banana', label: 'Banana' },
    //     { value: 'cherry', label: 'Cherry' },
    //     { value: 'grape', label: 'Grape' },
    //     { value: 'orange', label: 'Orange' }
    //   ];
    const options = [
        "apple",
        "Banana",
        "Cherry",
        "Grape"
    ].map((option) => ({ value: option, label: option }));


    const handleSelectChange = (selectedOptions) => {
        setSelectedOptions(selectedOptions);
        const values = selectedOptions.map(option => option.value);
        console.log(values);
    };

    return (
        <div className="homepage-container">
            <Navbar bg="lg" expand="lg" fixed="top" className="navbar-style" >
                <Link to="/"> <button className="logout-button"><i className="fa fa-sign-out"></i> Log out</button> </Link>
            </Navbar>
            <p className="welcome">WELCOME TO HOMEPAGE</p>

            <Form className="d-flex search">
                <Form.Control
                    type="search"
                    placeholder="Search by name"
                    className="me-2"
                    aria-label="Search"
                />
                {/* <Form.Control
              type="search"
              placeholder="Search by skill"
              className="me-2"
              aria-label="Search"
            /> */}
                <Select className="search-dropdown me-2"
                    placeholder={"Search by skills"}
                    options={options}
                    value={selectedOptions}
                    onChange={handleSelectChange}
                    isMulti
                />
                <button className="search-button" >Search</button>
            </Form>

            <CardGroup className="cards">
                <CandidateProfile></CandidateProfile>
                <CandidateProfile></CandidateProfile>
                <CandidateProfile></CandidateProfile> <CandidateProfile></CandidateProfile>
                <CandidateProfile></CandidateProfile>
                <CandidateProfile></CandidateProfile> </CardGroup>
        </div>
    )
}