import { Modal, Button, Form } from "react-bootstrap";
import React, { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from "react-toastify";
import CreatableSelect from 'react-select/creatable';
import { updateCandidate } from "../api/CandidateApi";
import '../styles/Homepage.css'
import { useEffect } from "react";
import { getAllSkills } from "../api/SkillApi";

export const UpdateCandidateForm = ({ show, candidate, id, handleClose, candidateSkillOptions, onUpdateNew }) => {
    const [fullName, setFullName] = useState(candidate.fullName);
    const [dateOfBirth, setDateOfBirth] = useState(candidate.dateOfBirth);
    const [email, setEmail] = useState(candidate.email);
    const [contactNumber, setContactNumber] = useState(candidate.contactNumber);
    const [availableSkills, setAvailableSkills] = useState([])
    const [selectedOptions, setSelectedOptions] = useState(candidateSkillOptions);
    const [skillList, setSkillListForCandidate] = useState([]);

    const [idCandidate, setId] = useState(id);

    useEffect(() => {
        getAllSkills().then((data) => {
            const options = data.data.map((option) => ({ value: option.name, label: option.name }));
            setAvailableSkills(options);
        }).catch(() => { })
    }, [])

    const handleSubmit = (event) => {
        event.preventDefault();
        const skillsForCandidate = selectedOptions.map((option) => ({ id: 0, name: option.value }));

        const updatedCandidate = {
            id,
            fullName,
            dateOfBirth,
            email,
            contactNumber,
            skillList
        }
        updateCandidate.id = idCandidate;
        updatedCandidate.skillList = skillsForCandidate;
        updateCandidate(updatedCandidate, idCandidate)
            .then(data => {
                toast.success('You successfully updated candidate!', { position: toast.POSITION.BOTTOM_CENTER });
                onUpdateNew(data.data)
                handleClose()
            });
    };

    const handleCreateOption = (inputValue) => {
        const newOption = {
            value: inputValue,
            label: inputValue,
        };
        console.log("U CREATE OPTION")
        setSelectedOptions([...selectedOptions, newOption]);
        setSkillListForCandidate(prevState => [...prevState, newOption]);
    };

    return (
        <div>
            <Modal show={show} centered>
                <Form onSubmit={handleSubmit}>
                    <Modal.Header closeButton>
                        <Modal.Title>Update candidate</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group controlId="fullName">
                            <Form.Label>Full Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter full name" value={fullName} onChange={(event) => setFullName(event.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="dateOfBirth">
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control type="date" placeholder="Enter date of birth" value={dateOfBirth} onChange={(event) => setDateOfBirth(event.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="email">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" value={email} onChange={(event) => setEmail(event.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="contactNumber">
                            <Form.Label>Contact Number</Form.Label>
                            <Form.Control type="text" placeholder="Enter contact number" value={contactNumber} onChange={(event) => setContactNumber(event.target.value)} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Select skills</Form.Label>
                            <CreatableSelect
                                isMulti
                                options={availableSkills}
                                value={selectedOptions}
                                onChange={(selectedOptions) => {
                                    setSelectedOptions(selectedOptions)
                                }}
                                onCreateOption={handleCreateOption} />
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <button type="submit" className="submit-button">Submit</button>
                        <Button variant="secondary" onClick={handleClose}>Close</Button>
                    </Modal.Footer> </Form>
            </Modal>
            <ToastContainer></ToastContainer>
        </div>
    )
}