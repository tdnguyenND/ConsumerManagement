let deleteFund = function (fundId) {
    return axios({
        url: 'http://' + window.location.host + "/fund/" + fundId,
        method: 'delete'
    })
}