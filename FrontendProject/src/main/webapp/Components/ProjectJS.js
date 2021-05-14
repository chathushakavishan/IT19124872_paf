$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateformCustomer();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ProjectAPI",
		type : t,
		data : $("#formCustomer").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onProjectSaveComplete(response.responseText, status);
			console.log(response);
		}
	});
}); 

function onProjectSaveComplete(response, status){
	if(status == "success")
	{
		console.log(response +  " "+status);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			console.log("dataaaaaa");
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	
	
	$("#hidProjectIDSave").val("");
	$("#formCustomer")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidProjectIDSave").val($(this).closest("tr").find('#hidprojectIDUpdate').val());     
	$("#Name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#Category").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#Budget").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#Description").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ProjectAPI",
		type : "DELETE",
		data : "ProjectID=" + $(this).data("Projectid"),
		dataType : "text",
		complete : function(response, status)
		{
			onProjectDeletedComplete(response.responseText, status);
		}
	});
});

function onProjectDeletedComplete(response, status)
{
	if(status == "success")
	{
		console.log(response);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateformCustomer() {  
	// NAME  
	if ($("#Name").val().trim() == "")  {   
		return "Insert fullName.";  
		
	} 
	
	 // Category 
	if ($("#Category").val().trim() == "")  {   
		return "Insert Category.";  
	} 
	
	
	// Budget  
	if ($("#Budget").val().trim() == "")  {   
		return "Insert Budget."; 
		 
	}
	 
	 // is numerical value  
	if  ($("#Description").val().trim() ==""); {   
		return "Insert Description.";  
		
	}
	 
	
		

	 
	 return true; 
	 
}
