import "../styles/Homepage.css"
import Navbar from "react-bootstrap/Navbar";
import 'font-awesome/css/font-awesome.min.css';
import { Link } from "react-router-dom";
import { CandidateProfile } from "./CandidateProfile";
import { Card, CardGroup } from "react-bootstrap";
import Form from 'react-bootstrap/Form';
import Select from 'react-select';
import React, { useEffect, useState } from 'react';
import { findByName, findBySkillsName, getAllCandidates } from "../api/CandidateApi";
import { getAllSkills } from "../api/SkillApi";
import { AddCandidateForm } from "./AddCandidate";
import { UpdateCandidateForm } from "./UpdateCandidate";

export const Homepage = () => {

    const [selectedOptions, setSelectedOptions] = useState([]);
    const [candidates, setCandidates] = useState([]);
    const [skillOptions, setSkillOptions] = useState([]);
    const [searchName, setSearchName] = useState("");
    const [searchMessage, setSearchMessage] = useState("");
    const [isSearchEmpty, setIsSearchEmpty] = useState(false);
    const [createUserModal, setCreateUserModal] = useState(false);

    const [updateCandidateFLEG, setFleg] = useState(false)
    const handleShowModal = () => {
        setCreateUserModal(true);
    }


    function searchCandidatesByName(event) {
        event.preventDefault();
        if (searchName === '') {
            console.log(searchName)
            getAllCandidates().then((data) => {
                setCandidates(data.data);
                setIsSearchEmpty(false);
            }).catch(() => { });
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

    function handleSelectChange(selectedOptions) {
        if (selectedOptions.length === 0) {
            console.log("ALO BRE")
            getAllCandidates().then((data) => {
                setCandidates(data.data);
                isSearchEmpty(false);
            }).catch(() => { });
        } else {
            console.log(selectedOptions)
            const values = selectedOptions.map(option => option.value);
            console.log("IZABRANE VESTINE U HANDLE SELECTION")
            console.log(values);
            findBySkillsName(values)
                .then(data => {
                    setCandidates(data.data);
                    console.log("VRACENI OK REZULTATI")
                    setIsSearchEmpty(false)
                })
                .catch(() => {
                    console.log("VRACENO BEZ REZULTATA")
                    setSearchMessage("No candidates with this search criteria. Please, try again.")
                    isSearchEmpty(true);
                    setCandidates([]);
                })
        }
    };

    useEffect(() => {
        getAllCandidates().then((data) => {
            setCandidates(data.data);
        }).catch(() => { });

        getAllSkills().then((data) => {
            const options = data.data.map((option) => ({ value: option.name, label: option.name }));
            setSkillOptions(options);
        }).catch(() => { })
    }, [])


    function handleOpenModal() {
        setCreateUserModal(true);
    }
    function handleCloseModal() {
        setCreateUserModal(false);
        getAllCandidates().then((data) => {
            setCandidates(data.data);
        }).catch(() => { });
        
        getAllSkills().then((data) => {
            const options = data.data.map((option) => ({ value: option.name, label: option.name }));
            setSkillOptions(options);
        }).catch(() => { })
    }

    function handleCandidateUpdate(updatedCandidate) {
        setFleg(true);
        console.log("EVO ME U HOMEPAGE UPDATE ")
        // const updatedCandidates = candidates.map(candidate => {
        //   if (candidate.id === updatedCandidate.id) {
        //     return updatedCandidate;
        //   } else {
        //     return candidate;
        //   }
        // });
        console.log(candidates)
        console.log("*******************************8")
        // setCandidates(updatedCandidates);
        getAllCandidates().then((data) => {
            setCandidates(data.data);
        }).catch(() => { });
      }
useEffect(()=> {
    getAllCandidates().then((data) => {
        setCandidates(data.data);
    }).catch(() => { });
}, [updateCandidateFLEG])
    return (
        <div className="homepage-container">
            <Navbar bg="lg" expand="lg" fixed="top" className="navbar-style" >
                <Link to="/"> <button className="logout-button"><i className="fa fa-sign-out"></i> Log out</button> </Link>
            </Navbar>
            <p className="welcome">WELCOME TO HOMEPAGE</p>

            <Form className="d-flex search" onSubmit={searchCandidatesByName}>
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
                    onChange={(selectedOptions) => {
                        setSelectedOptions(selectedOptions);
                    }}
                    onKeyDown={(event) => {
                        if (event.key === "Enter") {
                            event.preventDefault();
                            handleSelectChange(selectedOptions);
                        }
                    }}
                    isMulti
                />
                <button className="search-button"> Search</button>
                <button className="add-button" onClick={handleOpenModal}> <i class="fa fa-user-plus"></i> </button>
            </Form>
            
            { createUserModal ? <AddCandidateForm 
                show={createUserModal}
                handleClose = {handleCloseModal}
                skillOptions = {skillOptions}
                /> : ""}
            
            <CardGroup className="cards">
                {candidates && candidates.length > 0 ? candidates.map((candidate) => {
                    return (<CandidateProfile id={candidate.id}
                        fullName={candidate.fullName}
                        dateOfBirth={candidate.dateOfBirth}
                        email={candidate.email}
                        contactNumber={candidate.contactNumber}
                        skillList={candidate.skillList} 
                        onUpdate={handleCandidateUpdate}
                        />)
                }) : <p> </p>}
            </CardGroup> <div className="search-message">
                {isSearchEmpty ? <div><Card className="search-card">{searchMessage}</Card> </div> : null}
            </div>

        </div>
    )
}