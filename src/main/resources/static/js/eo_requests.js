
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
    window.setTimeout(function () {
		window.location="http://localhost:8076/pages/401.html";
	},4000);
}

function showViewAllEOPendingApplications(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/eo/viewAllVoterIdApplications");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
                if (response=="Access denied"){
                    authFailed();
                }
                else{
     
                    var temp = ``;
                    response.forEach(application => {
                        temp+=
                        `
                            <tr>
                            <td>`+application.userId+`</td>
                            <td>`+application.constituency+`</td>
                            <td><input type="checkbox" name="approve" value=`+application.userId+`></td>
                            <td><input type="checkbox" name="reject" value=`+application.userId+`></td>
                            </tr>
                        `
                    });
                        document.getElementById("showViewAllEOPendingApplicationsTableBody").innerHTML=temp;
                        document.getElementById("placeHolder").innerHTML = document.getElementById("showViewAllEOPendingApplications").innerHTML;
                        authSuccess();
                }
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}

function approve_reject(){
	var markedCheckboxApprove = document.getElementsByName('approve'); 
	var arrApprove = []; 
	for (var checkbox of markedCheckboxApprove) {  
    	if (checkbox.checked)  
        arrApprove.push(checkbox.value);  
    }   
    
	var markedCheckboxReject = document.getElementsByName('reject'); 
	var arrReject = []; 
	for (var checkbox of markedCheckboxReject) {  
    	if (checkbox.checked)  
        arrReject.push(checkbox.value);  
    }   
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/eo/generateVoterId");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"approve="+JSON.stringify(arrApprove)+"&"+"reject="+JSON.stringify(arrReject));
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
            	document.getElementById("placeHolder").innerHTML="";
                showViewAllEOPendingApplications();
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}

function changePasswordView() {
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/eo_auth");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
            	document.getElementById("placeHolder").innerHTML=document.getElementById("showChangePasswordView").innerHTML;
                document.getElementById("changePasswordUserId").value=localStorage.getItem("userId");
                document.getElementById("changePasswordPassword").value=localStorage.getItem("password");
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     } 
}
function changePassword() {
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/changePassword");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"newPassword="+document.getElementById("newPassword").value);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				authSuccess();
                logout();
	        }
	        else{
				
				authFailed();
			}
        }
     } 
}

function logout(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/logout");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				authSuccess();
                localStorage.removeItem("userId");localStorage.removeItem("password");
                window.location="http://localhost:8076/login";
	        }
	        else{
				
				authFailed();
			}
        }
    }
}