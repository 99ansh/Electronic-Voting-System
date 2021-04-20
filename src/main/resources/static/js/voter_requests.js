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

function showProfileView(){        
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/voter/profile");
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
					document.getElementById("placeHolder").innerHTML=document.getElementById("showProfileView").innerHTML;
			
					document.getElementById("userId").value=response.userId;
					document.getElementById("firstName").value=response.firstName;
					document.getElementById("lastName").value=response.lastName;
					document.getElementById("dateOfBirth").value=response.dateOfBirth;
					document.getElementById(response.gender).checked=true;
					document.getElementById("location").value=response.location;
					document.getElementById("city").value=response.city;
					document.getElementById("state").value=response.state;
					document.getElementById("pincode").value=response.pincode;
					document.getElementById("mobileNo").value=response.mobileNo;
					document.getElementById("emailId").value=response.emailId;
					document.getElementById("password").value=response.password;
	

					authSuccess();
				}
	        }
	        else{
				
				authFailed();
			}
        }
     }
}

function updateProfile(){
		var userId = localStorage.getItem("userId");
		var password = localStorage.getItem("password");
		var request = new XMLHttpRequest();
		var form = document.getElementById("userProfileForm");
		request.open("POST","http://localhost:8076/voter/updateProfile");
		request.setRequestHeader("content-type","application/x-www-form-urlencoded");
		var requestBody=``;
		//requestBody+="userId="+userId+"&";
		//requestBody+="password="+password+"&";
		requestBody+="userId="+form.elements["userId"].value+"&";
		requestBody+="firstName="+form.elements["firstName"].value+"&";
		requestBody+="lastName="+form.elements["lastName"].value+"&";
		requestBody+="dateOfBirth="+form.elements["dateOfBirth"].value+"&";
		requestBody+="gender="+form.elements["gender"].value+"&";
		requestBody+="location="+form.elements["location"].value+"&";
		requestBody+="city="+form.elements["city"].value+"&";
		requestBody+="state="+form.elements["state"].value+"&";
		requestBody+="pincode="+form.elements["pincode"].value+"&";
		requestBody+="mobileNo="+form.elements["mobileNo"].value+"&";
		requestBody+="emailId="+form.elements["emailId"].value+"&";
		requestBody+="password="+form.elements["password"].value;
		console.log(requestBody);
		request.send(requestBody);
		request.onreadystatechange = function() {
			if (this.readyState==4){
				if(this.status==200){
					console.log(this.response);
					document.getElementById("placeHolder").innerHTML="";
					showProfileView();
					authSuccess();
					// var response = JSON.parse(this.response);
					// console.log(response);
					// if(response==false){
					//     authFailed();
					// }
					// else{
					//     document.getElementById('tempLink').click();
					//     authSuccess();
					// }
				}
				else{
					
					authFailed();
				}
			}
		 }   
}

 function showVoterIdView(){        
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/voter/profile");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				var response = JSON.parse(this.response);
				if (response=="Access denied"){
					authFailed();
				}
				else{
					document.getElementById("placeHolder").innerHTML=document.getElementById("showVoterIdView").innerHTML;
					authSuccess();
				}
	
	        }
	        else{
				
				authFailed();
			}
        }
     }
  }

 function requestVoterId(){
	var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/voter/requestVoterId");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"constituency="+document.getElementById("voterIdConstituency").value);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				document.getElementById("requestVoterIdStatus").value=this.response;
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     }
}

function viewGeneratedVoterId(){
	var userId = localStorage.getItem("userId");
	var password = localStorage.getItem("password");
	var request = new XMLHttpRequest();
	request.open("POST","http://localhost:8076/voter/viewGeneratedVoterId");
	request.setRequestHeader("content-type","application/x-www-form-urlencoded");
	request.send("userId="+userId+"&"+"password="+password+"&"+"constituency="+document.getElementById("voterIdConstituency").value);
	request.onreadystatechange = function() {
		if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				document.getElementById("requestVoterIdStatus").value=this.response;
				authSuccess();
			}
			else{
				
				authFailed();
			}
		}
	}
}
 
function showElections(){
	var userId = localStorage.getItem("userId");
	var password = localStorage.getItem("password");
	var request = new XMLHttpRequest();
	request.open("POST","http://localhost:8076/voter/viewListofElections");
	request.setRequestHeader("content-type","application/x-www-form-urlencoded");
	request.send("userId="+userId+"&"+"password="+password);
	request.onreadystatechange = function() {
		if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                var temp = ``;
                response.forEach(election => {
                    temp+=
                    `
                        <tr>
                        <td>`+election.electionId+`</td>
                        <td>`+election.name+`</td>
                        <td>`+election.electionDate+`</td>
                        <td>`+election.district+`</td>
                        <td>`+election.constituency+`</td>
                        <td>`+election.countingDate+`</td>
                        </tr>
                    `
               });
			   

               document.getElementById("showViewAllUpcomingElectionsTableBody").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showViewAllUpcomingElections").innerHTML;
				authSuccess();
			}
			else{
				
				authFailed();
			}
		}
	}
}


function showAllCandidates(){
	var userId = localStorage.getItem("userId");
	var password = localStorage.getItem("password");
	var request = new XMLHttpRequest();
	request.open("POST","http://localhost:8076/voter/viewListofElections");
	request.setRequestHeader("content-type","application/x-www-form-urlencoded");
	request.send("userId="+userId+"&"+"password="+password);
	request.onreadystatechange = function() {
		if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				var temp=``;
				response.forEach(election => {
					temp+=
					`
						<option value=`+election.name+`>`+election.electionId+`-`+election.name+`</option>
					`
				});

				document.getElementById("electionNameCandidateView").innerHTML=temp;
				
            	document.getElementById("placeHolder").innerHTML=document.getElementById("showAllCandidates").innerHTML;
				authSuccess();
	
			}
			else{
				
				authFailed();
			}
		}
	}


}

function requestCandidates() {
	var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/voter/viewCandidatesByElectionName");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"eName="+document.getElementById("electionNameCandidateView").value+"&"+"cName="+document.getElementById("constituencyCandidateView").value);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                var temp = '';
                response.forEach(candidate => {
                    temp+=
                    `
                        <tr>
                        <td>`+candidate.candidateId+`</td>
                        <td>`+candidate.electionId+`</td>
                        <td>`+candidate.partyId+`</td>
                        <td>`+candidate.name+`</td>
                        <td>`+candidate.district+`</td>
                        <td>`+candidate.constituency+`</td>
                        <td>`+candidate.dateOfBirth+`</td>
                        <td>`+candidate.mobileNo+`</td>
                        <td>`+candidate.address+`</td>
                        <td>`+candidate.emailId+`</td>
                        </tr>
                    `
               });
			   if (temp==''){
				document.getElementById("showViewAllCandidatesTableBody").innerHTML="";
			   }
			   else{
				document.getElementById("showViewAllCandidatesTableBody").innerHTML=temp;
				document.getElementById("allCandidatesByelectionAndConstituency").innerHTML=document.getElementById("showViewAllCandidates").innerHTML;
					
				}
				authSuccess();
	
	        }
	        else{
				authFailed();
			}
        }
     }
}


function showCastVote(){
	var userId = localStorage.getItem("userId");
	var password = localStorage.getItem("password");
	var request = new XMLHttpRequest();
	request.open("POST","http://localhost:8076/voter/viewCastVote");
	request.setRequestHeader("content-type","application/x-www-form-urlencoded");
	request.send("userId="+userId+"&"+"password="+password);
	request.onreadystatechange = function() {
		if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				if (response=="Pending VoterId"){
					alert(response);
				}
				console.log(response);
				var userConstituency = response.constituency;
				console.log(response.elections);
				var elections = JSON.parse(response.elections);
				var temp=``;
				elections.forEach(election => {
					if (election.constituency==userConstituency){
						temp+=
						`
						<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts-`+election.electionId+`" style="color:rgba(0,0,0);" onMouseOver="this.style.color='rgba(0,0,0)'">
						<div id="loggedInAs" class="large">
							<div style="float: left;width: 98%;">
							`+election.electionId+` - `+election.name+` - `+election.electionDate+`
							</div>
							<div>
								<i class="fas fa-angle-down"></i>
							</div>
						</div>
						</a>
						`;
						var date1 = new Date();
						var dd = (date1.getDate() < 10 ? '0' : '')+ date1.getDate();
                        var MM = ((date1.getMonth() + 1) < 10 ? '0' : '')+ (date1.getMonth() + 1);
						var dateString1 = date1.getFullYear()+"-"+MM+"-"+dd;

						var date2 = new Date(election.electionDate);
						var dd = (date2.getDate() < 10 ? '0' : '')+ date2.getDate();
                        var MM = ((date2.getMonth() + 1) < 10 ? '0' : '')+ (date2.getMonth() + 1);
						var dateString2 = date2.getFullYear()+"-"+MM+"-"+dd;

						console.log(dateString1);
						console.log(dateString2);
						if(dateString1==dateString2){
							temp+=
							`
							<div class="collapse" id="collapseLayouts-`+election.electionId+`">
									<div class="table-responsive">
										<form action="http://localhost:8076/voter/castVote" method="POST">
										<input type='hidden' name='electionId' value='`+election.electionId+`'>
										<input type='hidden' name='userId' value='`+localStorage.getItem('userId')+`'>
										<input type='hidden' name='password' value='`+localStorage.getItem('password')+`'>
										<table class="table table-bordered" width="100%" cellspacing="0">
											<thead>
												<tr>
													<th>CandidateId</th>
													<th>Name</th>
													<th>PartyId</th>
													<th>Vote</th>
												</tr>
											</thead>
											<tbody id="`+election.electionId+`-tbody">
											</tbody>
										</table>
										<input type='submit' class='btn btn-primary' value='vote'> 
										</form>
									</div>
									
							</div>
							`;
						}
					}
				});

				document.getElementById("showCastVoteViewContent").innerHTML=temp;

				elections.forEach(election => {
					
					if (election.constituency==userConstituency){
						var date1 = new Date();
						var dd = (date1.getDate() < 10 ? '0' : '')+ date1.getDate();
                        var MM = ((date1.getMonth() + 1) < 10 ? '0' : '')+ (date1.getMonth() + 1);
						var dateString1 = date1.getFullYear()+"-"+MM+"-"+dd;

						var date2 = new Date(election.electionDate);
						var dd = (date2.getDate() < 10 ? '0' : '')+ date2.getDate();
                        var MM = ((date2.getMonth() + 1) < 10 ? '0' : '')+ (date2.getMonth() + 1);
						var dateString2 = date2.getFullYear()+"-"+MM+"-"+dd;
						
						console.log(dateString1);
						console.log(dateString2);
						if(dateString1==dateString2){
							
							var candidates = election.candidates;
							console.log(election.candidates);
							console.log(election.electionId,election.constituency,userConstituency,election.candidates);
							console.log("hello");
							var content=``;
							
							candidates.forEach(candidate => {
								if(candidate.constituency==userConstituency){
									
									content+=
									`
										<tr>
										<td>`+candidate.candidateId+`</td>
										<td>`+candidate.name+`</td>
										<td>`+candidate.partyId+`</td>
										<td><input type='radio' name='candidateId' value='`+candidate.candidateId+`'</td>
										</tr>
									`;
								}
							});

							document.getElementById(election.electionId+"-tbody").innerHTML=content;
						}
					}
				});


				document.getElementById("placeHolder").innerHTML = document.getElementById("showCastVoteView").innerHTML;
                
				authSuccess();
	
			}
			else{
				
				authFailed();
			}
		}
	}
}



function showElectionResults(){
	var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/voter/viewListOfElectionsResults");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                var temp = '';
                response.forEach(result => {
                    temp+=
                    `
                        <tr>
                        <td>`+result.serialNo+`</td>
                        <td>`+result.electionId+`</td>
                        <td>`+result.candidateId+`</td>
                        <td>`+result.voteCount+`</td>
                        </tr>
                    `
               });
			  
				document.getElementById("showElectionResultsTableBody").innerHTML=temp;
				document.getElementById("placeHolder").innerHTML=document.getElementById("showElectionResults").innerHTML;
			
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