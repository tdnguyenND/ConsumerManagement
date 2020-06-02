$('#fund-form').submit(event=>{
    event.preventDefault()
    createFund($('#fund-form').serialize())
        .then(response=>{
            if (response.data === 'fail') alert('Cannot create fund!');
            else {
                location.reload();
            }
        })
});