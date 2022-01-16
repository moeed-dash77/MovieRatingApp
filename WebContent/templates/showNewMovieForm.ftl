<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b><br><br>

<form method="POST" action="usergui?action=addNewMovie">
	<fieldset id="browseAvailableOffers">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<input type="text" name="username" >
	    </div>

		<div>
			<label>Title</label>
			<input type="text" name="title" >
	    </div>

		<div>
			<label>Director</label>
			<input type="text" name="director">
	    </div>
	    
	    <div>
			<label>Main Actor</label>
			<input type="text" name="mainActor">
	    </div>
	    
	    <div>
			<label>Publishing Date</label>
			<input type="text" name="publishingDate" id="datepicker1>
	    </div>
	</fieldset>
	<button type="submit" id="SelectHOWebpage" name="MOWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">