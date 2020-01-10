axios.get('/getAllChapter')
    .then(function (response) {
        var html = template('list_temp', {chapter:  response})
        $('#chapImg').html(html);
    })
    .catch(function (error) {
        console.log("获取数据失败"+error);
    });