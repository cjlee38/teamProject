import axios from "axios"

axios.defaults.baseURL = "http://127.0.0.1:5000/"
// axios.defaults.header = {}


export default {
    crawlInst(data) {
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
        return axios.get("/inst_update")
        }
}