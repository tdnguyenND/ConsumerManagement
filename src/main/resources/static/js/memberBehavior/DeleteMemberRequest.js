let deleteMember = function (fundId, username) {
    return axios({
        url: 'http://' + window.location.host + "/" + fundId + "/member/delete",
        method: 'post',
        params:{
            target: username
        }
    })
}