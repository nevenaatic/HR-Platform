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
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from "react-toastify";

export const Homepage = () => {

    const [selectedOptions, setSelectedOptions] = useState([]);
    const [candidates, setCandidates] = useState([]);
    const [skillOptions, setSkillOptions] = useState([]);
    const [searchName, setSearchName] = useState("");
    const [searchMessage, setSearchMessage] = useState("");
    const [isSearchEmpty, setIsSearchEmpty] = useState(false);
    const [createUserModal, setCreateUserModal] = useState(false);

    function searchCandidatesByName(event) {
        event.preventDefault();
        if (searchName === '') {
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
                })
        }
    }

    function handleSelectChange(selectedOptions) {
        if (selectedOptions.length === 0) {
            getAllCandidates().then((data) => {
                setCandidates(data.data);
                isSearchEmpty(false);
            }).catch(() => { });
        } else {
            const values = selectedOptions.map(option => option.value);
            findBySkillsName(values)
                .then(data => {
                    setCandidates(data.data);
                    setIsSearchEmpty(false)
                })
                .catch(() => {
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
        toast.success('You successfully updated candidate!', { position: toast.POSITION.BOTTOM_CENTER });
        getAllCandidates().then((data) => {
            setCandidates(data.data);
        });

    }

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
                <button className="add-button" onClick={handleOpenModal}> <i className="fa fa-user-plus"></i> </button>
            </Form>

            {createUserModal ? <AddCandidateForm
                show={createUserModal}
                handleClose={handleCloseModal}
                skillOptions={skillOptions}
            /> : ""}
            <CardGroup className="cards">
                {candidates && candidates.length > 0 ? candidates.map((c) => {
                    return (<CandidateProfile candidate={c}
                        onUpdate={handleCandidateUpdate}
                    />)
                }) : <p> </p>}
            </CardGroup> <div className="search-message">
                {isSearchEmpty ? <div><Card className="search-card">{searchMessage}</Card> </div> : null}
            </div>
        </div>
    )
}