<%-- 
    Document   : GReport4
    Created on : 16/11/2020, 21:08:14
    Author     : jara
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="DataBase.ConexionDB"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Reporte 4</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            String cd = String.valueOf(sesion.getAttribute("id"));
            int codigo = Integer.parseInt(cd);
            Connection cn = null;
            cn = ConexionDB.conector();
            PreparedStatement ps = null;
            ResultSet rs = null;
        %>
        <div class="container mt-5">  
            <div class="row">
                <div class="col-sm">   
                    <table class="table table-bordered table-dark table-hover table-striped">                      
                        <thead class="thead-dark"<>
                            <tr>
                                <th colspan="5">
                                    <h3>Los 10 clientes con más dinero en sus cuentas.</h3>
                                    <form action="/Project_Banco/ControladorReporteG4" method="GET">
                                        <input type="submit" class="btn btn-primary" value="Descargar">
                                    </form>
                                    <%
                                        String sql = "SELECT distinct cl.codigo,cl.nombre,cl.dpi,c.codigo,c.saldo FROM CLIENTE cl INNER JOIN CUENTA c ON cl.codigo = c.cliente  ORDER BY saldo DESC LIMIT 10;";
                                        ps = cn.prepareStatement(sql);
                                        rs = ps.executeQuery();
                                    %>
                                </th>
                            </tr>
                            <tr>
                                <th>Codigo</th>
                                <th>Cliente</th>
                                <th>DPI</th>
                                <th>Cuenta</th>
                                <th>Saldo</th>
                            </tr>
                        </thead>
                        <tbody>         
                            <%                                try {
                                    while (rs.next()) {
                            %>
                            <tr>
                                <td><%=rs.getInt("cl.codigo")%></td>
                                <td><%=rs.getString("cl.nombre")%></td>
                                <td><%=rs.getString("cl.dpi")%></td>
                                <td><%=rs.getInt("c.codigo")%></td>
                                <td><%=rs.getDouble("c.saldo")%></td>
                            </tr> 
                            <%
                                    }
                                } catch (SQLException e) {
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br>
    </body>
</html>
