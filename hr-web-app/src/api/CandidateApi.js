import axios from 'axios';

export async function getAllCandidates(){
    return await axios.get(`http://localhost:8081/candidates`);
}

export async function findByName(name){
    return await axios.get(`http://localhost:8081/candidates/candidate/${name}`);
}

export async function findBySkillsName(skils){
    const config = {
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'POST',
        }
    };
    return await axios.post('http://localhost:8081/candidates/search', {"skillList" : skils}, config);
}

export async function createCandidate(candidate){
    
    return await axios.post('http://localhost:8081/candidates/', candidate);
}

export async function updateCandidate(candidate, id) {
    return await axios.post(`http://localhost:8081/candidates/${id}`, candidate);

}

export async function deleteCandidate(id){
    return await axios.delete(`http://localhost:8081/candidates/${id}`);

}