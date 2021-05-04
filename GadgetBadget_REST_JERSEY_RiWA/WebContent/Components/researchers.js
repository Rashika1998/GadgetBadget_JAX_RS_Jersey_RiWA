 /**
 * 
 */
 
 $(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
 } 
 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
	 url : "ResearcherAPI", 
	 type : type, 
	 data : $("#formResearcher").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
 { 
 	onItemSaveComplete(response.responseText, status); 
 } 
 }); 
 
 
 function onItemSaveComplete(response, status)
 { 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show(); 
		 $("#divItemsGrid").html(resultSet.data); 
	 } 
	 else if (resultSet.status.trim() == "error") 
	 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	 } 
	 } 
	 else if (status == "error") 
	 { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } 
	 else
	 { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	 	 $("#hidItemIDSave").val(""); 
 		 $("#formResearcher")[0].reset(); 
 }
 
 
 
 $(document).on("click", ".btnUpdate", function(event)
 { 
	 $("#hidItemIDSave").val($(this).data("resid")); 
	 $("#resCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#resName").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#resEmail").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#resAge").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#resAddress").val($(this).closest("tr").find('td:eq(4)').text()); 
	 $("#resRole").val($(this).closest("tr").find('td:eq(5)').text()); 
	 $("#joinDate").val($(this).closest("tr").find('td:eq(6)').text()); 
 });
 
 
 
 
 
 $(document).on("click", ".btnRemove", function(event)
 { 
	 $.ajax( 
	 { 
		 url : "ResearcherAPI", 
		 type : "DELETE", 
		 data : "resID=" + $(this).data("resid"),
		 dataType : "text", 
		 complete : function(response, status) 
	 { 
	 	onItemDeleteComplete(response.responseText, status); 
	 } 
	 }); 
 });
 
 
 
 
 function onItemDeleteComplete(response, status)
 { 
	 if (status == "success") 
	 { 
	 	var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 } 
		 else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } 
		 else
		 { 
			 $("#alertError").text("Unknown error while deleting.."); 
			 $("#alertError").show(); 
		 } 
 }
 
 
 
 
 // CLIENT-MODEL================================================================
function validateItemForm() 
{ 
	// CODE
	if ($("#resCode").val().trim() == "") 
	 { 
	 	return "Insert researcher Code."; 
	 } 
	// NAME
	if ($("#resName").val().trim() == "") 
	 { 
	 	return "Insert researcher Name."; 
	 } 
	// Email
	if ($("#resEmail").val().trim() == "") 
	 { 
	 	return "Insert the E-mail address."; 
	 } 
	// Age------------------------
	if ($("#resAge").val().trim() == "") 
	 { 
	 	return "Insert the age."; 
	 }
	 if ($("#resAddress").val().trim() == "") 
	 { 
	 	return "Insert the address."; 
	 } 
	 if ($("#resRole").val().trim() == "") 
	 { 
	 	return "Insert the Role."; 
	 }
	 if ($("#joinDate").val().trim() == "") 
	 { 
	 	return "Insert the joined date."; 
	 }
	return true; 
}
 
 
 
 
 
});

 