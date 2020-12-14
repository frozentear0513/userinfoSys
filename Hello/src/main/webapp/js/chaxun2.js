$(function () {
    $("#button").click(function () {
        $.ajax({
            url:"/chaxun",   // 请求路径
            type:"get",            // 请求的方式，不区分大小写
            // async:true,             // 是否异步，true是默认值，false为同步请求
            // cache:false,            // 关闭缓存，目的是为了避免部分浏览器缓存加载出错(IE)
            // datatype:"html",        // 返回类型，text文本、html页面、json数据

            success:function(response){
                var json=JSON.parse(response)
                console.log(json);
                if(json.code===200){
                    var array=json.userList;
                    for(var i=0;i<array.length;i++){
                        $("#table1").append( " <tr>\n" +
                            "        <td>"+array[i].id+"</td>\n" +
                            "        <td>"+array[i].username+"</td>\n" +
                            "        <td>"+array[i].password+"</td>\n" +
                            "    </tr><br/>")
                    }
                }
            },
            error:function(response){
                alert("出错" + response);
            }
        });

    });
})
