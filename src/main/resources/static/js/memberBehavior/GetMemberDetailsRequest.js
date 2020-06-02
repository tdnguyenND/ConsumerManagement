let getMemberDetail = function (fundId, username) {
    return axios({
        url: 'http://' + window.location.host + "/" + fundId + "/member/details",
        method: 'get',
        params:{
            target: username
        }
    })
}