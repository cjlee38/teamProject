import axios from "axios"

axios.defaults.baseURL = "http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:5000"
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