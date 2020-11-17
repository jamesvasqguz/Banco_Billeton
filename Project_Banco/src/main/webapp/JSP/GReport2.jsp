<%@page import="java.sql.SQLException"%>
<%@page import="DataBase.DBRestrincciones"%>
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
        <title>Reporte 2</title>
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
            DBRestrincciones res = new DBRestrincciones();
        %>
        <div class="container mt-5">  
            <div class="row">
                <div class="col-sm">   
                    <table class="table table-bordered table-dark table-hover table-striped">                      
                        <thead class="thead-dark"<>
                            <tr>
                                <th colspan="5">
                                    <h3>Clientes con transacciones monetarias mayores a un límite establecido.</h3>
                                    <form action="/Project_Banco/ControladorReporteG2" method="GET">
                                        <input type="text" value="<%=codigo%>" name="cd" hidden>
                                        <input type="submit" class="btn btn-primary" value="Descargar">
                                    </form>
                                    <%
                                        int limite = res.limite1(codigo);
                                        String sql = "SELECT T.codigo,T.cuenta,I.nombre,T.fecha,T.monto FROM TRANSACCION T INNER JOIN CUENTA C ON T.cuenta=C.codigo AND T.monto>=? INNER JOIN CLIENTE I ON I.codigo=C.cliente;";
                                        ps = cn.prepareStatement(sql);
                                        ps.setInt(1, limite);
                                        rs = ps.executeQuery();
                                    %>
                                </th>
                            </tr>
                            <tr>
                                <th>Codigo Transaccion</th>
                                <th>Cuenta</th>
                                <th>Cliente</th>
                                <th>Fecha</th>
                                <th>Monto</th>
                            </tr>
                        </thead>
                        <tbody>         
                            <%                                try {
                                    while (rs.next()) {
                            %>
                            <tr>
                                <td><%=rs.getInt("T.codigo")%></td>
                                <td><%=rs.getInt("T.cuenta")%></td>
                                <td><%=rs.getString("I.nombre")%></td>
                                <td><%=rs.getString("T.fecha")%></td>
                                <td><%=rs.getDouble("T.monto")%></td>
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
