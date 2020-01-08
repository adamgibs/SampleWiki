

$(function(){
	function validate(string){
  	  return (string.split(",").indexOf("") === -1)? "valid": "invalid";
	}
	$("#saveBtnId").on('click',function(event){
		event.preventDefault();			
		var title = $("#titleId").val();
		var tags = $('#tagsId').val();
		
		var msg = "";

        if (title.trim().length == 0) {
            msg += "Title field is required! ";
        }
        
        if(validate(tags) == "invalid"){
        	msg += "Tags field must be a comma delimited list! ";
        }
        
        if (tags.trim().length == 0) {
            msg += "Tags field is required! ";
        }
        
        

       
        if (msg.trim().length != 0) {
        	alert(msg);
            return;
        }
		
		$("#newPostingFormId").submit();
	});
		
	$("#cancelBtnId").on('click',function(event){
		event.preventDefault();
		window.location = 'main';
	});	
	
});