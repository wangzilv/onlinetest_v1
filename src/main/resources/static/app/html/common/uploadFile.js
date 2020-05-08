$('#uploaderExample').uploader({
    autoUpload: true,            // 当选择文件后立即自动进行上传操作
    filters:{
        mime_types: [
            {title: '文档', extensions: 'xlsx,word'},
        ]
    },
    url: '/uploadFile/fileUpload'  // 文件上传提交地址
});