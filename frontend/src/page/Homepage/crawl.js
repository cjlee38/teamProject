import axios from "axios"

axios.defaults.baseURL = "http://127.0.0.1:5000"
// axios.defaults.header = {}


export default {
    crawlInst(data) {
        let options = {
            method: 'GET',
            url: "http://127.0.0.1:5000/inst_update",
            headers: {
                "Access-Control-Allow-Origin": "*"
            },
        };
        return axios.post("/inst_update", data)
    },

    crawlUser(data) {
        let config = {
            headers: {
                "Access-Control-Allow-Origin": "*"
            },
        };
        return axios.post('/user_profile', data, config)
    }
}
