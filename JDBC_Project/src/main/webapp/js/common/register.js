$(function () {


    $("#username").blur(function () {
        var username=$("#username").val();
        alert(username)
        if (username===""){
            $("#errorName").html("用户名不允许为空")
        }else {
            $.ajax({
                url:"/Loginfo?methodName=validateName",
                data:{"username":username},
                type:"POST",
                dataType:"json",
                success:function (data) {
                    if(data.status==0){
                        $("#errorName").html(data.message)
                    }else {
                        $("#errorName").html("可以使用")
                    }
                }
            })
        }
    })

})