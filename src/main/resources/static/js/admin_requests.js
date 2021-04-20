function authSuccess(){
	document.getElementById('auth-footer').innerHTML=" Authorized request ";
    document.getElementById('auth-footer').style.backgroundColor="green";
    document.getElementById('auth-footer').style.color="white";
    console.log("authorized");

}
function authFailed(){
    document.getElementById('auth-footer').innerHTML=" Un-authorized request ";
    document.getElementById('auth-footer').style.backgroundColor="red";
    document.getElementById('auth-footer').style.color="white";
    console.log("un - authorized");	
    localStorage.removeItem("userId");localStorage.removeItem("password");
    window.setTimeout(function () {
		window.location="http://localhost:8076/pages/401.html";
	},4000);	
}

function showAddElection(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin_auth");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML=document.getElementById("showAddElection").innerHTML;
                    authSuccess();
                }
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}

function addElection(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    var form = document.getElementById("addElectionForm");
    request.open("POST","http://localhost:8076/admin/addElection");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    var requestBody=``;
    // requestBody+="userId="+userId+"&";
    // requestBody+="password="+password+"&";
    if(form.elements["electionId"].value!=""){
        requestBody+="electionId="+form.elements["electionId"].value+"&";
    }
    requestBody+="name="+form.elements["name"].value+"&";
    requestBody+="electionDate="+form.elements["electionDate"].value+"&";
    requestBody+="district="+form.elements["district"].value+"&";
    requestBody+="constituency="+form.elements["constituency"].value+"&";
    requestBody+="countingDate="+form.elements["countingDate"].value;
    console.log(requestBody);
    request.send(requestBody);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                if (this.response=="Failed-ElectionDate after CountingDate"){
                    alert(this.responseText);
                }
                if(this.response=="Failed-Two elections same date"){
                    alert(this.responseText);
                }
                document.getElementById('allElections').click();
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


function showViewAllUpcomingElections(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var userType = localStorage.getItem("userType");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllUpcomingElections");
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

function showViewElections(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var userType = localStorage.getItem("userType");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllElections");
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
                        <td><button class="btn btn-primary" onclick="editElection(`+election.electionId+`)">Edit</button> <button class="btn btn-danger" onclick="deleteElection(`+election.electionId+`)">Delete</button>
                        </tr>
                    `
               });

               document.getElementById("showViewElectionsTableBody").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showViewElections").innerHTML;
				authSuccess();
	
	        }
	        else{
				
				authFailed();
			}
        }
     }

}

function editElection(electionId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/getElection");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"electionId="+electionId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML=document.getElementById("showAddElection").innerHTML;
                    var form = document.getElementById("addElectionForm");
                    form.elements["electionId"].value=response["electionId"];
                    form.elements["name"].value=response.name;
                    form.elements["electionDate"].value=response.electionDate;
                    form.elements["district"].value=response.district;
                    form.elements["constituency"].value=response.constituency;
                    form.elements["countingDate"].value=response.countingDate;
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}

function deleteElection(electionId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/deleteElection");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"electionId="+electionId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML="";
                    showViewElections();
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}

function showAddParty(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin_auth");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML=document.getElementById("showAddParty").innerHTML;
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
     }   
}


function addParty(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    var form = document.getElementById("addPartyForm");
    request.open("POST","http://localhost:8076/admin/addParty");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    var requestBody=``;
    // requestBody+="userId="+userId+"&";
    // requestBody+="password="+password+"&";
    if(form.elements["partyId"].value!=""){
        requestBody+="partyId="+form.elements["partyId"].value+"&";
    }
    requestBody+="name="+form.elements["name"].value+"&";
    requestBody+="leader="+form.elements["leader"].value+"&";
    requestBody+="symbol="+form.elements["symbol"].value;
    console.log(requestBody);
    request.send(requestBody);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                document.getElementById('allParties').click();
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
function editParty(partyId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/getParty");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"partyId="+partyId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML=document.getElementById("showAddParty").innerHTML;
                    var form = document.getElementById("addPartyForm");
                    form.elements["partyId"].value=response.partyId;
                    form.elements["name"].value=response.name;
                    form.elements["leader"].value=response.leader;
                    form.elements["symbol"].value=response.symbol;
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}

function deleteParty(partyId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/deleteParty");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"partyId="+partyId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML="";
                    showViewAllParty();
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}
function showViewAllParty(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllParty");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                var temp = ``;
                response.forEach(party => {
                    temp+=
                    `
                        <tr>
                        <td>`+party.partyId+`</td>
                        <td>`+party.name+`</td>
                        <td>`+party.leader+`</td>
                        <td>`+party.symbol+`</td>
                        <td><button class="btn btn-primary" onclick="editParty(`+party.partyId+`)">Edit</button> <button class="btn btn-danger" onclick="deleteParty(`+party.partyId+`)">Delete</button>
                        
                        </tr>
                    `
               });

               document.getElementById("showViewAllPartyTableBody").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showViewAllParty").innerHTML;
				authSuccess();
	
	        }
	        else{
				
				authFailed();
			}
        }
     }

}
function showAddCandidate(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/addCandidateView");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
				console.log(response.electionMap);
				console.log(response.partyMap);
				var electionMap = JSON.parse(response.electionMap);
				var partyMap = JSON.parse(response.partyMap);
                var temp=``;
				for (x in electionMap) {
					temp+=`<option value='`+x+`'>`+electionMap[x]+`</option>`;
				}
                document.getElementById("electionOptions").innerHTML=temp;
                temp=``;
                for (x in partyMap) {
					temp+=`<option value='`+x+`'>`+partyMap[x]+`</option>`;
				}
                document.getElementById("partyOptions").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showAddCandidate").innerHTML;
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}


function addCandidate(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    var form = document.getElementById("addCandidateForm");
    request.open("POST","http://localhost:8076/admin/addCandidate");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    var requestBody=``;
    // requestBody+="userId="+userId+"&";
    // requestBody+="password="+password+"&";
    if(form.elements["candidateId"].value!=""){
        requestBody+="candidateId="+form.elements["candidateId"].value+"&";
    }
    requestBody+="name="+form.elements["name"].value+"&";
    requestBody+="electionId="+form.elements["electionId"].value+"&";
    requestBody+="partyId="+form.elements["partyId"].value+"&";
    requestBody+="district="+form.elements["district"].value+"&";
    requestBody+="constituency="+form.elements["constituency"].value+"&";
    requestBody+="dateOfBirth="+form.elements["dateOfBirth"].value+"&";
    requestBody+="mobileNo="+form.elements["mobileNo"].value+"&";
    requestBody+="address="+form.elements["address"].value+"&";
    requestBody+="emailId="+form.elements["emailId"].value;
    console.log(requestBody);
    request.send(requestBody);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                document.getElementById('allCandidates').click();
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

function editCandidate(candidateId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/getCandidate");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"candidateId="+candidateId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML=document.getElementById("showAddCandidate").innerHTML;
                    var form = document.getElementById("addCandidateForm");
                    form.elements["candidateId"].value=response.candidateId;
                    form.elements["electionId"].value=response.electionId;
                    form.elements["partyId"].value=response.partyId;
                    form.elements["name"].value=response.name;
                    form.elements["district"].value=response.district;
                    form.elements["name"].value=response.name;
                    form.elements["constituency"].value=response.constituency;
                    form.elements["dateOfBirth"].value=response.dateOfBirth;
                    form.elements["mobileNo"].value=response.mobileNo;
                    form.elements["address"].value=response.address;
                    form.elements["emailId"].value=response.emailId;
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}

function deleteCandidate(candidateId){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/deleteCandidate");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"candidateId="+candidateId);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                if(response==false){
                    authFailed();
                }
                else{
                    document.getElementById("placeHolder").innerHTML="";
                    showViewAllCandidates();
                    authSuccess();
                }
	        }
	        else{
				authFailed();
			}
        }
    }
}


function showViewAllCandidates(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllCandidates");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                var response = JSON.parse(this.response);
				console.log(response);
				console.log(response.electionMap);
				console.log(response.partyMap);
				var electionMap = JSON.parse(response.electionMap);
				var partyMap = JSON.parse(response.partyMap);
                var temp=``;
				for (x in electionMap) {
					temp+=`<option value='`+x+`'>`+electionMap[x]+`</option>`;
				}
                document.getElementById("electionIdCandidateView").innerHTML=temp;
                temp=``;
                for (x in partyMap) {
					temp+=`<option value='`+x+`'>`+partyMap[x]+`</option>`;
				}
                document.getElementById("partyIdCandidateView").innerHTML=temp;

                var candidates = JSON.parse(response.candidates);
                var temp = ``;
                candidates.forEach(candidate => {
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
                        <td><button class="btn btn-primary" onclick="editCandidate(`+candidate.candidateId+`)">Edit</button> <button class="btn btn-danger" onclick="deleteCandidate(`+candidate.candidateId+`)">Delete</button>
                        
                        </tr>
                    `
               });

               document.getElementById("showViewAllCandidatesTableBody").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showAllCandidates").innerHTML;
				authSuccess();
	
	        }
	        else{
				authFailed();
			}
        }
     }

}


function showViewAllCandidatesByElection(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllCandidates");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                var response = JSON.parse(this.response);
				console.log(response);
				console.log(response.electionMap);
				console.log(response.partyMap);
				
                var electionId = document.getElementById("electionIdCandidateView").value;
               
                var candidates = JSON.parse(response.candidates);
                var temp = ``;
                candidates.forEach(candidate => {
                    if (candidate.electionId==electionId){
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
                            <td><button class="btn btn-primary" onclick="editCandidate(`+candidate.candidateId+`)">Edit</button> <button class="btn btn-danger" onclick="deleteCandidate(`+candidate.candidateId+`)">Delete</button>
                            
                            </tr>
                        `
                    }
               });

               document.getElementById("showViewAllCandidatesTableBody").innerHTML=temp;

				authSuccess();
	
	        }
	        else{
				authFailed();
			}
        }
     }

}


function showViewAllCandidatesByParty(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllCandidates");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
                var response = JSON.parse(this.response);
				console.log(response);
				console.log(response.electionMap);
				console.log(response.partyMap);
				
                var partyId = document.getElementById("partyIdCandidateView").value;
               
                var candidates = JSON.parse(response.candidates);
                var temp = ``;
                candidates.forEach(candidate => {
                    if (candidate.partyId==partyId){
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
                            <td><button class="btn btn-primary" onclick="editCandidate(`+candidate.candidateId+`)">Edit</button> <button class="btn btn-danger" onclick="deleteCandidate(`+candidate.candidateId+`)">Delete</button>
                            
                            </tr>
                        `
                    }
               });

               document.getElementById("showViewAllCandidatesTableBody").innerHTML=temp;

				authSuccess();
	
	        }
	        else{
				authFailed();
			}
        }
     }

}

function showViewAllAdminPendingApplications(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllAdminPendingApplications");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password);
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
                var temp = ``;
                response.forEach(application => {
                    temp+=
                    `
                        <tr>
                        <td>`+application.userId+`</td>
                        <td>`+application.constituency+`</td>
                        <td><input type="checkbox" name="forwardRequest" value=`+application.userId+`></td>
                        </tr>
                    `
               });
               document.getElementById("showViewAllAdminPendingApplicationsTableBody").innerHTML=temp;
				//console.log(response.electionMap);
				// var electionMap = JSON.parse(response.electionMap);
				// for (x in electionMap) {
				// 	console.log(x);
				//   document.getElementById("placeHolder").innerHTML += electionMap[x];
				// }
            	document.getElementById("placeHolder").innerHTML=document.getElementById("showViewAllAdminPendingApplications").innerHTML;
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}

function forwardVoterIdRequests(){
	var markedCheckbox = document.getElementsByName('forwardRequest'); 
	var arr = []; 
	for (var checkbox of markedCheckbox) {  
    	if (checkbox.checked)  
        arr.push(checkbox.value);  
    }   
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var userType = localStorage.getItem("userType");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/forwardVoterIDRequest");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"requests="+JSON.stringify(arr));
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
            	document.getElementById("placeHolder").innerHTML="";
                showViewAllAdminPendingApplications();
				authSuccess();
	        }
	        else{
				
				authFailed();
			}
        }
     }   
}



function showApproveElectionResults(){
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var userType = localStorage.getItem("userType");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/viewAllElections");
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
                        <td><input type="checkbox" name="appElection" value="`+election.electionId+`"></td>
                        </tr>
                    `
               });

               document.getElementById("showApproveElectionResultsTableBody").innerHTML=temp;

            	document.getElementById("placeHolder").innerHTML=document.getElementById("showApproveElectionResults").innerHTML;
				authSuccess();
	
	        }
	        else{
				
				authFailed();
			}
        }
     }

}

function approveElections(){
    var electionIds = [];
    var temp=document.getElementsByName("appElection");
    temp.forEach(x => {
        if (x.checked){
            electionIds.push(x.value);
        }
    });
    var userId = localStorage.getItem("userId");
    var password = localStorage.getItem("password");
    var request = new XMLHttpRequest();
    request.open("POST","http://localhost:8076/admin/approveElectionResults");
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("userId="+userId+"&"+"password="+password+"&"+"eIds="+JSON.stringify(electionIds));
    console.log("userId="+userId+"&"+"password="+password+"&"+"eIds="+JSON.stringify(electionIds));
    request.onreadystatechange = function() {
        if (this.readyState==4){
			if(this.status==200){
				console.log(this.response);
				var response = JSON.parse(this.response);
				console.log(response);
               
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