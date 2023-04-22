
import Card from "react-bootstrap/Card";
import Button from 'react-bootstrap/Button';
import '../styles/Homepage.css';
import { useState } from "react";



export const CandidateProfile = () =>{
    const [skills, setSkills] = useState(["Java", "python"]);

  return (
  <div className="card-style">  <Card style={{ width: '20rem' }}>
    <Card.Body>
      <Card.Title>Card Title</Card.Title>
      <Card.Text style={{ fontSize: 16 }}>
        Some quick example text to build on the card title and make up the
        bulk of the card's content.
      
      </Card.Text> 
     <div>  { skills.map(skill => <label className="skill-label">cao</label>)}</div> 
      <Button variant="primary">Edit</Button> <Button variant="primary">Delete</Button>
    </Card.Body>
  </Card>
  </div>
  );
}
