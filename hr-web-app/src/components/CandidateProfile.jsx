
import Card from "react-bootstrap/Card";
import Button from 'react-bootstrap/Button';
import '../styles/Homepage.css';
import { useState } from "react";
import moment from "moment/moment";
import { useEffect } from "react";


export const CandidateProfile = (candidate, skills1) => {
  const [skills, setSkills] = useState(["Java", "python"]);


  // const checkIfHasSkills = () =>{
  //   console.log(candidate)
  //   if(candidate.skillList)
  //   setSkills(candidate.skillList)
  //   else setSkills([]);
  // }


  return (
    <div className="card-style">  <Card style={{ width: '24rem' }}>
      <Card.Body>
        <Card.Title>{candidate.fullName}</Card.Title>
        <Card.Text style={{ fontSize: 16 }}>
          <p>Email: {candidate.fullName}</p>
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
        <Button variant="primary">Edit</Button> <Button variant="primary">Delete</Button>
      </Card.Body>
    </Card>
    </div>
  );
}
