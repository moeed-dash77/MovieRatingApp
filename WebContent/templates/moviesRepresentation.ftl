<#include "MOWebpage.ftl">

<b>Welcome to our little demonstration on the MR Webapp</b>

<table id="availableHOs">
	<tr>
		<th>#</th>
		<th>Title</th>
		<th>Publishing Date</th>
		<th>Director</th>
		<th>Main Actor</th>
		<th>Average Rating</th>
		<th>Comments</th>
	</tr>
	<#list allMovies as m>
	<tr>
		<td>${m.title}</td>
		<td>${m.publishingDate}</td>
		<td>${m.director}</td>
		<td>${m.mainActor}</td>
		<td>${m.Avg_Rating}</td>
		<td>${m.comment}</td>
	</tr>
	</#list>
</table>
<br>
<br>

<#include "footer.ftl">