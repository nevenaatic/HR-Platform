import { UpdateCandidateForm } from "./UpdateCandidate";
import Card from "react-bootstrap/Card";
import 'font-awesome/css/font-awesome.min.css';
import '../styles/Homepage.css';
import { useState } from "react";
import moment from "moment/moment";
import { useEffect } from "react";
import { DeleteCandidate } from "./DeleteCandidate";


export const CandidateProfile = ({candidate, onUpdate}) => {
  const [updateUserModal, setUpdateUserModal] = useState(false);
  const [skillOptions, setSkillOptions] = useState([]);
  const [candidateId, setCandidateId] = useState(candidate.id)
  const [deleteUserModal, setDeleteModal] = useState(false)

  const handleOpenModal = () =>{
    setUpdateUserModal(true);
  }

  const handleOpenDeleteModal = () =>{
    setDeleteModal(true);
  }

  function handleCloseModal() {
    onUpdate(null)
    setUpdateUserModal(false);
    setDeleteModal(false);
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
        <hr/>
        <buttton className="submit-button" onClick={handleOpenModal}><i class="fa fa-user-pen"> </i>Edit </buttton> 
        <button className="delete-button" onClick={handleOpenDeleteModal}><i class="fa fa-trash" > </i>Delete</button>
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

{ deleteUserModal ? <DeleteCandidate 
                show={deleteUserModal}
                candidate = {candidate}
                id= {candidateId}
                handleClose = {handleCloseModal}
                /> : ""}
    </div>
  );
}
