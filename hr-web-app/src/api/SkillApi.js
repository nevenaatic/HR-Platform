import axios from 'axios';

export async function getAllSkills(){
    return await axios.get(`http://localhost:8081/skills`);
}