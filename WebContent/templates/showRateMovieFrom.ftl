<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b><br><br>

<form method="POST" action="usergui?action=rateMovie">
	<fieldset id="browseAvailableOffers">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<input type="text" name="username" >
	    </div>

		<div>
			<label>Rating</label>
			<input type="text" name="rating" >
	    </div>

		<div>
			<label>Title</label>
			<input type="text" name="title">
	    </div>
	    
	    <div>
			<label>Comment</label>
			<input type="text" name="comment">
	    </div>
	</fieldset>
	<button type="submit" id="SelectHOWebpage" name="MOWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">