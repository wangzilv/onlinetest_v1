axios.get('/User/getUserInfo',)
    .then(function (response) {
        var html = template('profile', {user:  response})
        $('#profile-form').html(html);
    })
    .catch(function (error) {
        console.log(error);
    });