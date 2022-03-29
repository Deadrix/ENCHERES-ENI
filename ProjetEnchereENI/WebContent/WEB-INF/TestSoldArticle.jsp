<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
 <head>
      <meta charset="utf-8">
      <title>AddItem</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
   </head>
   <body>
      <!-- Button to Open the Modal -->
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
           Add an Item to Sell
      </button>

      <!-- The Modal -->
      <div class="modal" id="myModal">
         <div class="modal-dialog">
            <div class="modal-content">
               <!-- Modal Header -->
               <div class="modal-header">
                  <h4 class="modal-title">Add an Item</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
               </div>
               <!-- Modal body -->
               <div class="modal fade" id="add_item" aria-hidden="true"
               aria-labelldby="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
					<div class="modal-header">
		       </div>
		       
		       <div class="modal-body">
					<form action="<%=request.getContextPath()%>/AddItem" method="post"
						id="add_item">
               <!-- Modal footer -->
               <div class="modal-footer">
                  <button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
               </div>
            </div>
         </div>
      </div>

      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   </body>
  
</html>