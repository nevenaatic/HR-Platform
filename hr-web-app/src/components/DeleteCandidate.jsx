import 'font-awesome/css/font-awesome.min.css';
import '../styles/Homepage.css';
import { Modal, Button } from "react-bootstrap";
import { deleteCandidate } from "../api/CandidateApi";

export const DeleteCandidate = ({ show, id, handleClose }) => {

    const deleteUser = () => {
        deleteCandidate(id).then(() => {
            handleClose()
        })
    }

    return (
        <Modal show={show} centered>
            <Modal.Header >
                <Modal.Title>Delete</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p> Are you sure you want to delete this candidate?</p>
            </Modal.Body>
            <Modal.Footer>
                <button type="submit" className="submit-button" onClick={deleteUser}>Yes</button>
                <Button variant="secondary" onClick={handleClose}>No</Button>
            </Modal.Footer>
        </Modal>
    );
}
