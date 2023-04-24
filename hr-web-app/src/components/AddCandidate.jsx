import { Modal, Button, Form } from "react-bootstrap";
import React, { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import CreatableSelect from 'react-select/creatable';
import { createCandidate } from "../api/CandidateApi";
import { ToastContainer, toast } from "react-toastify";
import '../styles/Homepage.css'

export const AddCandidateForm = ({ show, handleClose, skillOptions }) => {
    const [fullName, setFullName] = useState('');
    const [dateOfBirth, setDateOfBirth] = useState('');
    const [email, setEmail] = useState('');
    const [contactNumber, setContactNumber] = useState('');
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [skillList, setSkillListForCandidate] = useState([]);



    const handleSubmit = (event) => {
        event.preventDefault();
        const newCandidate = {
            fullName,
            dateOfBirth,
            email,
            contactNumber,
            skillList
        };
        const skillsForCandidate = selectedOptions.map((option) => ({ id: 0, name: option.value }));
        console.log(skillsForCandidate);
        newCandidate.skillList = skillsForCandidate;
        console.log(newCandidate);
        createCandidate(newCandidate).then(data => {
            toast.success('You successfully create candidate! ', { position: toast.POSITION.BOTTOM_CENTER })
            handleClose()
        }).catch(() => {
            toast.error('Candidate with this email already exist! ', { position: toast.POSITION.BOTTOM_CENTER })
        })
    }

    // const handleSelectChange = (newValue, actionMeta) => {
    //     setSelectedOptions(newValue);
    //     console.log(newValue);
    //     console.log("U HANDLE SELECTION")
    //     console.log(selectedOptions)
    //   };

    const handleCreateOption = (inputValue) => {
        const newOption = {
            value: inputValue,
            label: inputValue,
        };
        console.log("U CREATE OPTION")
        setSelectedOptions([...selectedOptions, newOption]);
        setSkillListForCandidate(prevState => [...prevState, newOption]);
        console.log(selectedOptions)
        console.log(skillList)

    };


    return (
        <div>
            <Modal show={show} centered>
                <Modal.Header closeButton>
                    <Modal.Title>Add New Candidate</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group controlId="fullName">
                            <Form.Label>Full Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter full name" value={fullName} onChange={(e) => setFullName(e.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="dateOfBirth">
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control type="date" placeholder="Enter date of birth" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="email">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control type="email" placeholder="Enter email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        </Form.Group>
                        <Form.Group controlId="contactNumber">
                            <Form.Label>Contact Number</Form.Label>
                            <Form.Control type="text" placeholder="Enter contact number" value={contactNumber} onChange={(e) => setContactNumber(e.target.value)} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Select skills</Form.Label>
                            <CreatableSelect
                                isMulti
                                options={skillOptions}
                                value={selectedOptions}
                                onChange={(selectedOptions) => {
                                    setSelectedOptions(selectedOptions)
                                }}
                                onCreateOption={handleCreateOption} />
                        </Form.Group>

 <button type="submit" className="submit-button">Submit</button>

                    </Form>
                </Modal.Body>
                <Modal.Footer>
                   
                    <Button variant="secondary" onClick={handleClose}>Close</Button>
                </Modal.Footer>
            </Modal>
            <ToastContainer></ToastContainer>
        </div>
    )
}