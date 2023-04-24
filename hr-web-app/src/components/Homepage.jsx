import "../styles/Homepage.css"
import Navbar from "react-bootstrap/Navbar";
import 'font-awesome/css/font-awesome.min.css';
import { Link } from "react-router-dom";
import { CandidateProfile } from "./CandidateProfile";
import { Card, CardGroup } from "react-bootstrap";
import Form from 'react-bootstrap/Form';
import Select from 'react-select';
import React, { useEffect, useState } from 'react';
import { findByName, getAllCandidates } from "../api/CandidateApi";
import { getAllSkills } from "../api/SkillApi";

export const Homepage = () => {

    const [selectedOptions, setSelectedOptions] = useState([]);
    const [candidates, setCandidates] = useState([]);
    const [skillOptions, setSkillOptions] = useState([]);
    const [searchName, setSearchName] = useState("");
    const [searchMessage, setSearchMessage] = useState("");
    const [isSearchEmpty, setIsSearchEmpty] = useState(false);

    function searchCandidatesByName(event){
        event.preventDefault();
        if(searchName===''){
            console.log(searchName)
            getAllCandidates().then((data) => {
                setCandidates(data.data);
                setIsSearchEmpty(false);
            }).catch(() => {});
        } else {
             findByName(searchName)
            .then((data) => setCandidates(data.data), setIsSearchEmpty(false))
            .catch((err) => {
                setSearchMessage("No candidates with this search criteria. Please, try again.")
                setCandidates([])
                setIsSearchEmpty(true);
                console.log(searchMessage)
            })
        }
       
    }   

    const handleSelectChange = (selectedOptions) => {
        setSelectedOptions(selectedOptions);
        const values = selectedOptions.map(option => option.value);
    };

    useEffect(() => {
        getAllCandidates().then((data) => {
            setCandidates(data.data);
        }).catch(() => {});

        getAllSkills().then((data) => {
            const options = data.data.map((option) => ({ value: option.name, label: option.name }));
            setSkillOptions(options);
        }).catch(() => { })
    }, [])




    return (
        <div className="homepage-container">
            <Navbar bg="lg" expand="lg" fixed="top" className="navbar-style" >
                <Link to="/"> <button className="logout-button"><i className="fa fa-sign-out"></i> Log out</button> </Link>
            </Navbar>
            <p className="welcome">WELCOME TO HOMEPAGE</p>

            <Form className="d-flex search"  onSubmit={searchCandidatesByName}>
                <Form.Control
                    type="search"
                    placeholder="Search by name and press enter"
                    className="me-2"
                    aria-label="Search"
                    value={searchName}
                  onChange={(event) => setSearchName(event.target.value)}
                
                />
                <Select className="search-dropdown me-2"
                    placeholder={"Search by skills"}
                    options={skillOptions}
                    value={selectedOptions}
                    onChange={handleSelectChange}
                    isMulti
                />
                <button className="search-button" > Search</button>
            </Form>

            <CardGroup className="cards">
                {candidates.map((candidate) => {
                    return (<CandidateProfile id={candidate.id}
                        fullName={candidate.fullName}
                        dateOfBirth={candidate.dateOfBirth}
                        email={candidate.email}
                        contactNumber={candidate.contactNumber}
                        skillList={candidate.skillList} skills1={candidate.skillList} />)
                })}
            </CardGroup> <div className="search-message"> 
                 { isSearchEmpty ? <div><Card className="search-card">{searchMessage}</Card> </div> : null}
            </div>
           
        </div>
    )
}