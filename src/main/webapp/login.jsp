<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${APP_PATH }/js/jquery-3.2.1.min.js"></script>
</head>
<body>

	<h1>Login Page</h1>
	<form method="post" id="login-form">
		username : <input type="text" name="username"/> <br/>
		password : <input type="password" name="password"/> <br/>
		<button id="submit">登录</button>
	</form>
	
	<script type="text/javascript">
		
		$(function(){
			$("#submit").click(function(){
				$.ajax({
					url : '${APP_PATH}/login',
					method : 'post',
					data : $("#login-form").serialize(),
					success : function(result){
						//失败处理
						if(result.code == 200){
							alert(result.msg);
							return;
						}
						//成功进行跳转
						window.location.href="list.jsp";
					}
				})
			})
		})
	
	</script>
	
</body>
</html>