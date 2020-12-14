
$(function () {
    $("#zhuce").click(function () {
        var username=$("#username").val()
        var password=$("#password").val()
        console.log(username+"    " + password)
        $.ajax({
            url:"/zhuce",           // 请求路径
            type:"Post",            // 请求的方式，不区分大小写
            async:true,             // 是否异步，true是默认值，false为同步请求
            cache:false,            // 关闭缓存，目的是为了避免部分浏览器缓存加载出错(IE)
            datatype:"json",        // 返回类型，text文本、html页面、json数据
            data:{
                username:username,
                password:password
            },
            success:function(response){
                var json=JSON.parse(response)
                console.log(response)
                if(json.code===200){
                    if (confirm("注册成功!")){
                        window.location.href = "yemiandenglu.html";
                    }
                }
                else {
                    alert("注册失败");
                }

            },
            error:function(response){
                alert("出错" + response);
            }
        });
    })
});







