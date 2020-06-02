let transferTreasurer = function (fundId, username) {
    return axios({
        url: 'http://' + window.location.host + "/" + fundId + "/member/transfer-treasurer",
        method: 'post',
        params:{
            target: username
        }
    })
}