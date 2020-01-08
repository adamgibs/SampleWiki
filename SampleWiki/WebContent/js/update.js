
$(function(){
	$("#updateBtnId").on('click',function(event){
		event.preventDefault();			
		var title = $("#titleId").val();
		var msg = "";

        if (title.trim().length == 0) {
            msg += "Title field is required! ";
        }
        
        if (msg.trim().length != 0) {
        	alert(msg);
            return;
        }
		
		$("#updatePostingFormId").submit();
	});
		
	$("#cancelBtnId").on('click',function(event){
		event.preventDefault();
		window.location = 'main';
	});	
	
});