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
                      <li><a href="UpdateVoterAccount" title="Update your Password">Update Account</a></li>
                      <li><a href="VoterPanel" title="Go to your Profile">Go to Profile</a></li>
                      <li><a href="SelectParty" title="View a Candidate's details">Candidate Profile</a></li>
                      <li><a href="SelectPartyQues" title="Ask Questions from Candidates">Ask a Question</a></li>
                      <li><a href="HashPassGenerate" title="Get HashPassword for Voting">Get HashPassword</a></li>
                      <li><a href="VoterLogout">Logout</a></li>
                    </ul>
                    </div>
</body>
</html>
