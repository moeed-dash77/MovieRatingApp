<!DOCTYPE HTML>
<html lang='de' dir='ltr'>
<head>
	<meta charset="utf-8" />
	<title>Movie Rating App - ${pagetitle}</title>
	<link type="text/css" href="css/style.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
  		$( function() {
    			$( "#datepicker" ).datepicker();
					} );
  	</script>
</head>
<body>
<div id="wrapper">
	<div id="logo">Movie Rating App<br>Software Engineering </div>
    <ul id="navigation">
    	<li><a href="index" title="Index">View Homesite</a></li>
		<li><a href="MOWebpage?action=selectAddMovieForm&username=${username}" title="AddNewMovie" id= "AddMovie">Add Movie</a></li>
		<li><a href="MOWebpage?action=showAllMoviesInDB&username=${username}" title="MoviesInDatabase" id="ShowMovies">Show all Movies</a></li>
		<li><a href="defaultWebpageUser?action=selectRegisterForm" title="Register" id= "Resgister">Register</a></li>
    </ul>
	<div id="content">