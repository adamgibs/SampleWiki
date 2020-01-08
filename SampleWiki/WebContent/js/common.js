$(function(){
	
	$('.confirmation').on('click', function () {
		
        return confirm('Delete this entry?');
    });	
		
	$("#loginLnkId").on('click',function(event){
		event.preventDefault();
		window.location = 'login';
	});
	
	$("#registerLnkId").on('click',function(event){
		event.preventDefault();
		window.location = 'register';
	});
	
	
	$("#logoutLnkId").on('click',function(event){
		event.preventDefault();
		document.cookie = "userId=; expires= Thu, 01 Jan 1970 00:00:00 UTC" ;
		window.location = 'main';
	});
	
	$("#showAllBtnId").on('click',function(event){
		event.preventDefault();
		window.location = 'main';
	});
});