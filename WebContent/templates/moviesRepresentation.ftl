<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b>

<table id="MovieListTable">
	<tr>
		<th>Title</th>
		<th>Publishing Date</th>
		<th>Director</th>
		<th>Main Actor</th>
		<th>Average Rating</th>
		<th>Rate Movies</th>
	</tr>
	<#list moviesList as m>
	<tr>
		<td>${m.title}</td>
		<td>${m.publishingDate}</td>
		<td>${m.director}</td>
		<td>${m.mainActor}</td>
		<td>${m.avgRating}</td>
		<td><a href="MOWebpage?action=selectRateMoviesForm&username=${username}&movieTitle=${m.title}" id=${m.title}>Rate This Movie</a></td>
	</tr>
	</#list>
</table>
<br>
<br>

<#include "footer.ftl">