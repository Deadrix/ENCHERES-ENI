<%@ include file="fragments/HeaderAndNavBar.jspf"%>

<script type="text/javascript">

function EnableDisableCalendar(){
	
	var enchere_differe = document.getElementById("enchere_differe");
	var calendar = document.getElementById("calendar");
	calendar.disabled = enchere_differe.checked ? false : true;
	var dateTime = new Date.now().toISOString().substring(0, 16);
	document.getElementById("calendar").setAttribute('min', dateTime);
	
}
</script>

<%-- <c:set var = "category" value ="${SoldArticle.category }"></c:set> --%>
<%-- <c:set var = "auctionStart" value ="${SoldArticle.auctionStart }"></c:set> --%>
<%-- <c:set var = "auctionEnd" value ="${SoldArticle.auctionEnd }"></c:set> --%>
<%-- <c:set var = "initalPrice" value ="${SoldArticle.initialPrice }"></c:set> --%>
<%-- <c:set var = "soldPrice" value ="${SoldArticle.soldPrice }"></c:set> --%>
<%-- <c:set var = "seller" value="${SoldArticle.seller}"></c:set> --%>
<%-- <c:set var = "buyer" value="${SoldArticle.buyer}"></c:set> --%>

<form class="SoldArticle" action="ServletTestSoldArticle" method="POST">


<!-- Titre Article -->
  	<div class="input-field">
          <label for="article">Article :</label>
          <input class="input" type="text" name="article" id="article"  placeholder="Article" required>
         </div>

<!-- Descriptiopn -->
        <div class="input-field">
          <label for="description">Description :</label>
          <textarea class="input"name="description" id="description" placeholder="Entrez une description de l'article"></textarea>
        </div>

<!-- Début Enchères -->
		
		<div>
			<input type="radio" id="enchere_immediat" name="boutondébut" value="immédiat" onclick="EnableDisableCalendar()" checked>
			<label for="date_debut_enchere">Mise en vente immediate</label>
		</div>
		<div>	
			<div>
			<input type="radio" id="enchere_differe" name="boutondébut" value="différé" onclick="EnableDisableCalendar()">
			<label for="date_debut_enchere">Date de mise en vente</label>
				  <div>
				 
				    <label for="date_debut_enchere">Veuillez choisir une date et une heure du débt des enchères :</label>
				    <input id="calendar" type="datetime-local" name="auctiondate" required disabled="disabled">
				    <span class="validity"></span>
				  </div>
				  
				  <div>
				 
				  </div>
				
	</div>>
		
		
		
		</div>


<!-- Ajout Photo -->
    <div class="input-field">
          <label for="file">Photo de l'article :</label>
          <div class="input"> 
            <input type="file" name="file" id="file" accept="image/*"> <!--Only helps the user, need to put protection in the backend-->
            
          </div>
        </div>
        
        
        
        
        
        
       <input type="submit"> 
</form>        
        
        