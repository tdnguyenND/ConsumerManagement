let fundId = window.location.pathname.split('/')[1];
let renderTransaction = function(transaction) {
    $('#detail-actor').html(transaction['actor']);
    $('#detail-date').html(transaction['dateOfCreation']);
    $('#detail-money').html(transaction['amountOfMoney']);
    $('#detail-name').html(transaction['name']);
    $('#detail-note').html(transaction['note']);
    $('#detail-type').html(transaction['type']);
};

let showTransaction = function(){
    getTransactionInfo(fundId, this.dataset['id'])
        .then(response => renderTransaction(response.data))
};

$('.trans-item').click(showTransaction)

$('#create-transaction').submit(event=>{
    event.preventDefault();
    createTransaction(fundId, $('#create-transaction').serialize())
});

$('#leave-fund').click(event=>{
   event.preventDefault();
   leaveFund(fundId).then(response=>{
       if (response.data === 'fail') alert('You can not leave this fund now!');
       else {
           window.location = window.location.hostname + '/home';
       }
   })
});