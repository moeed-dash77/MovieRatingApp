<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b><br><br>
<div><b>Movie: ${movietitle}</b><br>
<b>User: ${username}</b><br>
</div>
<form method="POST" action="MOWebpage?action=rateMovie&username=${username}&movieTitle=${movietitle}">
	<fieldset id="RateMovies">
		<legend>Required Information</legend>
		<div>
			<label>Rating</label>
			<input type="text" name="rating" >
	    </div>
	    <div>
			<label>Comment</label>
			<input type="text" name="comment">
	    </div>
	</fieldset>
	<button type="submit" id="RateMovie" name="MOWebpage" value="Submit">Submit!</button>
</form>

<#include "footer.ftl">