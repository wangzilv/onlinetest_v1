$('#uploaderProblems').uploader({
    autoUpload: true,            // 当选择文件后立即自动进行上传操作
    filters:{
        mime_types: [
            {title: '文档', extensions: 'xlsx,xls'},
        ]
    },
    url: '/uploadFile/batchProblemUpload'  // 文件上传提交地址
});
$('#uploaderTestPaper').uploader({
    autoUpload: true,            // 当选择文件后立即自动进行上传操作
    filters:{
        mime_types: [
            {title: '文档', extensions: 'xlsx,xls'},
        ]
    },
    url: '/uploadFile/batchTestPaperUpload'  // 文件上传提交地址
});