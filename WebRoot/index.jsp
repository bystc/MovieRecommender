<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Movie Recommend</title>
  </head>
  
  <body>
    <center>
    <form action="RecomendServlet" method="post">
    <table border="0">
    <tr><td align="right">Enter UserID</td><td align="left"><input type="text" name="userID"></td></tr>
    <tr><td align="right">Enter Size</td><td align="left"><input type="text" name="size" value="25"></td></tr>
    <tr><td colspan="2" align="center">
    <input type="radio" name="recommendType" checked="checked" value="userBased">User Based
    <input type="radio" name="recommendType" value="itemBased">Item Based
    <input type="radio" name="recommendType" value="slopeOne">Slope One
    </td></tr>
    <tr><td colspan="2" align="center"><input type="submit" value="submit"></td></tr>
    </table>
    </form>
    </center>
  </body>
</html>
