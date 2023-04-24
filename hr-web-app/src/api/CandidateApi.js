import axios from 'axios';

export async function getAllCandidates(){
    return await axios.get(`http://localhost:8081/candidates`);
}