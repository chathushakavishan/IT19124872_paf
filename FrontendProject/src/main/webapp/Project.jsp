<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/ProjectJS.js"></script>

<meta charset="ISO-8859-1">
<title>Project</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Project Management</h1>        
				
				<form id="formCustomer" name="formCustomer" method="post" action="ProjectAPI">  
					Name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					Category:  
					<input id="category" name="categoy" type="text" class="form-control form-control-sm">  
					
					<br>
					 Budget:  
					 <input id="budget" name="budget" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Description:  
					 <input id="description" name="description" type="text" class="form-control form-control-sm">  
					 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="submit" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="Project.jsp"> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divItemsGrid">   
					<%    
						Project projectObj = new Project();
						out.print(projectObj.readProject());   
					%>  
					
					<br>
					<br>
					
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>