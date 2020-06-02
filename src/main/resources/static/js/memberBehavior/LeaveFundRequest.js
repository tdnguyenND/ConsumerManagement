let leaveFund = function (fundId) {
    return axios({
        url: 'http://' + window.location.host + "/" + fundId + "/member/leave",
        method: 'get'
    })
}