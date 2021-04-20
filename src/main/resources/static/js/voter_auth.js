function authSuccess(){
	document.getElementById('auth-footer').innerHTML="Authorized request";
    document.getElementById('auth-footer').style.backgroundColor="green";
    document.getElementById('auth-footer').style.color="white";
    console.log("authorized");
}
function authFailed(){
    document.getElementById('auth-footer').innerHTML="Un-authorized request";
    document.getElementById('auth-footer').style.backgroundColor="red";
    document.getElementById('auth-footer').style.color="white";
    console.log("un - authorized");
    localStorage.removeItem("userId");localStorage.removeItem("password");
    window.location="http://localhost:8076/pages/401.html";		
}
function auth(){        
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var userType = localStorage.getItem("userType");
    var authReqest = new XMLHttpRequest();
    authReqest.open("POST","http://localhost:8076/voter_auth",true);
    authReqest.setRequestHeader("content-type","application/x-www-form-urlencoded");
    authReqest.onreadystatechange = function() {
        if (this.readyState==4){
			if (this.status==200){
	            if (this.responseText=="true"){
					authSuccess();
	            }
	            else if(this.responseText=="false"){
					authFailed();
	            }
        	}
        }
     }
    authReqest.send("userId="+userId+"&"+"password="+password+"&"+"userType="+userType);
  }    
