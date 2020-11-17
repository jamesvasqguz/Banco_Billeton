<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="DataBase.ConexionDB"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Reporte 5</title>
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
                                    <h3>Clientes que no han realizado transacciones dentro de un intervalo de tiempo.</h3>
                                    <form action="/Project_Banco/ControladorReporteG5" method="GET">
                                        <input type="date" name="f1" placeholder="Fecha 1">
                                        <input type="date" name="f2" placeholder="Fecha 2">
                                        <input type="submit" class="btn btn-primary" value="Descargar">
                                    </form>
                                    <%
                                        String fecha1 = request.getParameter("f1");
                                        String fecha2 = request.getParameter("f2");
                                        String sql = "SELECT cl.codigo,cl.nombre,cl.sexo,cl.dpi from CLIENTE cl INNER JOIN  (SELECT * FROM CUENTA c WHERE NOT EXISTS (SELECT * FROM TRANSACCION t WHERE c.codigo = t.cuenta AND t.fecha BETWEEN ? AND ?)) AS b ON b.cliente = cl.codigo;";
                                        ps = cn.prepareStatement(sql);
                                        ps.setString(1, fecha1);
                                        ps.setString(2, fecha2);
                                        rs = ps.executeQuery();
                                    %>
                                </th>
                            </tr>
                            <tr>
                                <th>Codigo </th>
                                <th>Nombre</th>
                                <th>DPI</th>
                                <th>Sexo</th>
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
                                <td><%=rs.getString("cl.sexo")%></td>
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
