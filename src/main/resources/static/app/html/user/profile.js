
function _getStudentInfo(uid){
    var data={
        uid:uid
    }
    axios.post('/Student/getStudentInfo',data)
        .then(function (response) {
            var html = template('profile', {stuinfo:  response})
            $('#profile-form').html(html);
            $('.gender').each(function () {
                var _this = this;
                if( response.gender== $(_this).val()){
                    $(_this).prop('checked','checked');
                }
            })
        })
        .catch(function (error) {
            console.log(error);
        });
}
