import axios from "axios"

axios.defaults.baseURL = "http://127.0.0.1:5000"
// axios.defaults.header = {}


export default {
    crawlInst(data) {
        let options = {
            method: 'POST',
            url: "http://127.0.0.1:5000/inst_update",
            headers: {
                "Access-Control-Allow-Origin": "*"
            },
        };
        return axios.post("/inst_update", data)
    },

    async crawlUser(data) {
        let config = {
            headers: {
                "Access-Control-Allow-Origin": "*"
            },
        };
        let result = await axios.post('/user_profile', data, config)

        return result

    },

    async get_inst() {
        let options = {
            method: 'GET',
            url: "http://127.0.0.1:5000/inst_update",
            headers: {
                "Access-Control-Allow-Origin": "*"
            },
        };
        return axios.get("/inst_update")
        }
}