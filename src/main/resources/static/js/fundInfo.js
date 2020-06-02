let fundId = window.location.pathname.split('/')[1];

function renderMemberInfo(member) {
    $('#member-username').html(member['username']);
    $('#member-name').html(member['firstName'] + ' ' + member['lastName']);
    $('#member-email').html(member['email']);
    $('#member-gender').html(member['gender']);
    $('#member-phoneNumber').html(member['phoneNumber'])
}

$('.member-item').click(showDetails)

function showDetails() {
    getMemberDetail(fundId, this.dataset['username'])
        .then(response => renderMemberInfo(response.data))
}

$('#add-member-form').submit(event=>{
    event.preventDefault();
    addMember(fundId, $('#member-to-add').val())
        .then(response => {
            if (response.data === 'fail') alert('You can not add this member now!');
            else{
                location.reload();
            }
        });
});

$('#transfer-treasurer').click(event=>{
    event.preventDefault();
    transferTreasurer(fundId, $('#member-username').html())
        .then(response => {
            if (response.data === 'fail') alert('You can not transfer treasurer now!');
            else{
                location.reload();
            }
        });
})

$('#delete-member').click(event=>{
    event.preventDefault();
    deleteMember(fundId, $('#member-username').html())
        .then(response => {
            if (response.data === 'fail') alert('You can not delete this member now!');
            else {
                location.reload();
            }
        });
})