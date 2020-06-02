let getFundInfo = function (fundId) {
    return axios({
        url:'http://' + window.location.host + "api/fund/" + fundId,
        method:'get'
    })
};