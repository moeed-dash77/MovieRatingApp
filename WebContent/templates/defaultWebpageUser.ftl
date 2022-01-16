<#include "header.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b><br><br>

<form method="POST" action="defaultWebpageUser?action=registerUser">
	<fieldset id="browseAvailableOffers">
		<legend>Required Information</legend>
		<div>
			<label>Username</label>
			<input type="text" name="username" >
	    </div>

		<div>
			<label>email</label>
			<input type="text" name="email" >
	    </div>

		<div>
			<label>Password</label>
			<input type="password" name="password">
	    </div>
	    
	    <div>
			<label>age</label>
			<input type="text" name="age">
	    </div>
	</fieldset>
	<button type="submit" id="SelectHOWebpage" name="SelectMRWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">