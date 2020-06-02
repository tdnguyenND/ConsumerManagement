let getTransactionInfo = function (fundId, transactionId) {
    axios({
        url:'http://' + window.location.host + "api/" + fundId + "/transaction/" + transactionId,
        method: 'get'
    })

}