let addMember = function (fundId, username) {
    return axios({
        url: 'http://' + window.location.host + "/" + fundId + "/member/add",
        method: 'post',
        params:{
            target: username
        }
    })
}