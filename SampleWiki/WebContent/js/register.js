$(function(){
	$("#submitBtnId").on('click', function(event){
		event.preventDefault();
		var firstName = $("#firstNameId").val();
		var lastName = $("#lastNameId").val();
		var username = $("#userNameId").val();
		var password = $("#passwordId").val();
		
		if(username.trim().length == 0 || password.trim().length == 0 || firstName.trim().length == 0 || lastName.trim().length == 0 ){
			$("#errMessage").empty().append("Invalid input!!!")
			return;
		}
		
		$("#registerFormId").submit();
		
	});
	
	$("#cancelBtnId").on('click', function(event){
		event.preventDefault();
		window.location = 'main'; //
	});
	
	
	
});