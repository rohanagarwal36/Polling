<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Menu</title>
<style type="text/css">
body {
        font-family: Verdana, Arial, Helvetica, sans-serif;
        margin: 0;
        font-size: 80%;
        font-weight: bold;
        background: #FFA655;
        }
ul {
        list-style: none;
        margin: 0;
        padding: 0;
        }
#menu6 {
        width: 200px;
        margin: 10px;
		float:left;
		padding-top:20px;
        }

#menu6 li a {
          height: 32px;
          voice-family: inherit;
          height: 24px;
          text-decoration: none;
        }
#menu6 li a:active {
        color:darkmagenta;
        }
#menu6 li a:link, a:visited{
        color: #8D9179;
        display: block;
        background: url(menu6.gif);
        padding: 8px 0 0 10px;
        }

#menu6 li a:hover {
        color: #6C7250;
        background: url(menu6.gif) 0 -32px;
        padding: 8px 0 0 10px;
        }
        
-->
</style>
</head>

<body>

                  <div id="menu6">
                    <ul>
                      <li><a href="home.jsp">Home</a></li>
                      <li><a href="UpdateCandidateAccount" title="Change your password">Update Account</a></li>
                      <li><a href="CandidatePanel" title="Go to your profile">Go to Profile</a></li>
                      <li><a href="EditCandidateProfile" title="Change Account Details">Edit Profile</a></li>
                      <li><a href="ViewQuestionsAction" title="View Questions asked to you by Voters">View Questions</a></li>
                      <li><a href="AddPromisesAction2" title="Add and Edit your promises">Add|Edit Promises</a></li>
                      <li><a href="CandidateLogout">Logout</a></li>
                    </ul>
                    </div>
</body>
</html>

