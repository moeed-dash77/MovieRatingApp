<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b><br><br>

<form method="POST" action="MOWebpage?action=addNewMovie&username=${username}">
	<fieldset id="addNewMoviesToDB">
		<legend>Required Information</legend>
		<div>
			<label>Movie Title</label>
			<input type="text" name="title" required>
	    </div>

		<div>
			<label>Director</label>
			<input type="text" name="director" required>
	    </div>
	    
	    <div>
			<label>Main Actor</label>
			<input type="text" name="mainActor" required>
	    </div>
	    
	    <div>
			<label>Publishing Date</label>
			<input type="text" name="publishingDate" id="datepicker" required>
	    </div>
	</fieldset>
	<button type="submit" id="AddMovieForm" name="MOWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">