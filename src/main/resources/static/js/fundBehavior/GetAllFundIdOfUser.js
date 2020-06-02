function getAllFundId() {
    return axios({
        url:'http://' + window.location.host + "api/fund/getAll",
        method: 'get'
    })
}