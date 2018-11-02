<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- <script src="vendor/jquery-validation/dist/jquery.validate.min.js"></script>	 -->

<script>
	$(document).ready(function() {
		getContactList();
	});

	function getContactList() {
		$.ajax({
					type : "GET",
				//	url : '/ContactApplication/employees',
					url : '/employees',
					dataType : "json",
					crossDomain : true,
					success : function(data) {
						console.log(data);
						var row;
						for (var i = 0; i < data.length; i++) {
							row = '<tr id="'+data[i].id+'"><td>'
									+ data[i].name
									+ '</td><td>'
									+ data[i].designation
									+ '</td><td>'
									+ data[i].expertise
									+ '</td><td>'
									+ data[i].adr.state
									+ '</td><td>'
									+ data[i].adr.pincode
									+ '</td><td><button onclick="setUpdateForm('
									+ data[i].id
									+ ')">Edit</button></td><td><button onclick="deleteContact('
									+ data[i].id
									+ ')">Delete</button></td></tr>';
							$("#contactrows").append(row);
						}
					},
					error : function(data) {
						console.log("error");
					}
				});
	}

	function deleteContact(id) {
		$.ajax({
			type : "DELETE",
			//url : '/ContactApplication/employees/' + id,
			url : '/employees/' + id,
			crossDomain : true,
			success : function(data) {
				$("tr#" + id).remove();
			},
			error : function(data) {
				console.log("error");
			}
		});
	}
	
	function isPinValid(elem)
	{    
	    var re = /^[0-9]+$/; 
	    str = elem.toString();
	    if (!str.match(re)) 
	    {      
	    	alert("Please enter valid pin code.");  
	        return false;
	    }
	    return true;
	}
	function addContact() {
		var name = $("#txtName").val();
		var txtDes = $("#txtDes").val();
		var txtExp = $("#txtExp").val();
		var txtState=$("#txtState").val();
		var txtPin=$("#txtPin").val();

	      if(!isPinValid(txtPin ))
	      {    
	          return false;
	      }

		

		$.ajax({
					type : 'POST',
					//url : '/ContactApplication/employees',
					url : '/employees',
					contentType : 'application/json;charset=UTF-8',
					data : '{"name": "' + name+'","designation": "'+txtDes
						+'","expertise": "' + txtExp
						+'","adr": {"state": "'+txtState
							+'","pincode": " '+txtPin+'"}}',
						
					dataType : 'JSON',
					crossDomain : true,
					success : function(resdata) {
						var row = '<tr id="'+resdata.id+'"><td>'
								+ resdata.name
								+ '</td><td>'
								+ resdata.designation
								+ '</td><td>'
								+ resdata.expertise
								+ '</td><td>'
								+ resdata.adr.state
								+ '</td><td>'
								+ resdata.adr.pincode
								+ '</td><td><button onclick="setUpdateForm('
								+ resdata.id
								+ ')">Edit</button></td><td><button onclick="deleteContact('
								+ resdata.id + ')">Delete</button></td></tr>';
						$("#contactrows").append(row);

						$("#txtName").val("");
						$("#txtDes").val("");
						$("#txtExp").val("");
						$("#txtState").val("");
						$("#txtPin").val("");
						
					},
					error : function(errdata) {
						console.log("error");
					}
				});
		
	}

	function updateContact() {
		var name = $("#txtName").val();
		var txtDes = $("#txtDes").val();
		var txtExp = $("#txtExp").val();
		var txtId = $("#txtId").val();
		var txtState=$("#txtState").val();
		var txtPin=$("#txtPin").val();
		if(!isPinValid(txtPin ))
	      {    
	          return false;
	      }
		
		
		
		$
				.ajax({
					type : 'PUT',
					//url : '/ContactApplication/employees/' + txtId,
					url : '/employees/' + txtId,
					contentType : 'application/json;charset=UTF-8',
					data :'{"name": "' + name+'","designation": "'+txtDes
					+'","expertise": "' + txtExp
					+'","adr": {"state": "'+txtState
						+'","pincode": " '+txtPin+'"}}',
						
						/* '{"name": "' + name + '","designation": "' + txtDes
							+ '","expertise": "' + txtExp + '"}', */
							
							
							
					dataType : 'JSON',
					crossDomain : true,
					success : function(resdata) {
						var row = '<tr id="'+resdata.id+'"><td>'
								+ resdata.name
								+ '</td><td>'
								+ resdata.designation
								+ '</td><td>'
								+ resdata.expertise
								+ '</td><td>'
								+ resdata.adr.state
								+ '</td><td>'
								+ resdata.adr.pincode
								+ '</td><td><button onclick="setUpdateForm('
								+ resdata.id
								+ ')">Edit</button></td><td><button onclick="deleteContact('
								+ resdata.id + ')">Delete</button></td></tr>';
						$("#" + txtId).replaceWith(row);

						$("#txtName").val("");
						$("#txtDes").val("");
						$("#txtExp").val("");
						$("#txtState").val("");
						$("#txtPin").val("");
						
						$("#btnAdd").prop('disabled', false);
						$("#btnUpdate").prop('disabled', true);
					},
					error : function(errdata) {
						console.log("error");
					}
				});
	}
	
	function setUpdateForm(id) {
		$("#txtId").val(id);
		
		var txtname=$("tr#" + id).find('td').eq(0).html();
		$("#txtName").val(txtname);
		
		var txtDes=$("tr#" + id).find('td').eq(1).html();
		$("#txtDes").val(txtDes);
		
		var txtExp=$("tr#" + id).find('td').eq(2).html();
		$("#txtExp").val(txtExp);
		
		var txtState=$("tr#" + id).find('td').eq(3).html();
		$("#txtState").val(txtState);
		
		var txtPin=$("tr#" + id).find('td').eq(4).html();
		$("#txtPin").val(txtPin);
		/* $("#17").find('td').eq(0).html(); */
		$("#btnAdd").prop('disabled', true);
		$("#btnUpdate").prop('disabled', false);
	}
</script>
<body>
	<div>
		<input id="txtName" type="text" placeholder="Name"  ><br> 
		<input id="txtDes" type="text" placeholder="Designation" ><br>
		<input id="txtExp" type="text" placeholder="Expertise"><br> 
		<input id="txtState" type="text" placeholder="State" ><br>
		<input id="txtPin"  type="text" placeholder="Pin Code"><br> 
		<input id="txtId" type="hidden"><br>

		<button id="btnAdd" onclick="return addContact()">Add</button>
		<button id="btnUpdate" onclick="updateContact()" disabled>Update</button>
	</div>

	<div id="contactdetails">
		<table style="border: 2px black solid; width: 100%">
			<thead>
				<tr>
					<th>Name</th>
					<th>Designation</th>
					<th>Expertise</th>
					<th>State</th>
					<th>Pin</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody id="contactrows" >
			</tbody>
		</table>
	</div>
</body>
</html>