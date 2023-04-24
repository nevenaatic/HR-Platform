import { UpdateCandidateForm } from "./UpdateCandidate";
import Card from "react-bootstrap/Card";
import Button from 'react-bootstrap/Button';
import '../styles/Homepage.css';
import { useState } from "react";
import moment from "moment/moment";
import { useEffect } from "react";


export const CandidateProfile = (candidate, onUpdate) => {
  const [updateUserModal, setUpdateUserModal] = useState(false);
  const [skillOptions, setSkillOptions] = useState([]);
  const [candidateId, setCandidateId] = useState(candidate.id)


  const handleOpenModal = () =>{
    setUpdateUserModal(true);
  }

  function handleCloseModal() {
    console.log(":ZATVARAM SE")
    onUpdate(null)
    setUpdateUserModal(false);
}

useEffect(() =>{
  const options = candidate.skillList.map((skill) =>({ value: skill.name, label: skill.name }))
  setSkillOptions(options);
}, [])

  return (
    <div className="card-style">  <Card style={{ width: '24rem' }}>
      <Card.Body>
        <Card.Title>{candidate.fullName}</Card.Title>
        <Card.Text style={{ fontSize: 16 }}>
          <p>Email: {candidate.email}</p>
          <p>Number: {candidate.contactNumber}</p>
          <p>Birthday: {candidate.dateOfBirth ? moment(candidate.dateOfBirth).format('DD-MM-YYYY') : "/"}</p>
        </Card.Text>
        <div>
          {
            (() => {
              const elements = [];
              for (const skill of candidate.skillList) {
                elements.push(<label className="skill-label">{skill.name}</label>);
              }
              return elements;
            })()
          }

        </div>
        <Button variant="primary" onClick={handleOpenModal}>Edit</Button> <Button variant="primary">Delete</Button>
      </Card.Body>
    </Card>
    { updateUserModal ? <UpdateCandidateForm 
                show={updateUserModal}
                candidate = {candidate}
                id= {candidateId}
                handleClose = {handleCloseModal}
                candidateSkillOptions = {skillOptions}
                onUpdateNew={onUpdate}
                /> : ""}
    </div>
  );
}
