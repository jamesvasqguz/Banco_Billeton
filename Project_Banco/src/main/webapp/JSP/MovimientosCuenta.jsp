<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.DBConsultas"%>
<%@page import="Backend.Transaccion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Movimientos</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            String cd = String.valueOf(sesion.getAttribute("id"));
            int codigo = Integer.parseInt(cd);
            int cliente = Integer.parseInt(request.getParameter("clienteM"));
            int cuenta = Integer.parseInt(request.getParameter("cuentaM"));
            Transaccion t = new Transaccion();
            DBConsultas cons = new DBConsultas();
            ArrayList<Transaccion> list = cons.movimientos(cuenta);
        %>
        <div class="container mt-5">  
            <div class="row">
                <div class="col-sm">   
                    <table class="table table-dark table-bordered table-striped">                      
                        <thead>
                            <tr>
                                <th colspan="5" class="text-center"><h2>Movimientos de mi Cuenta</h2></th>
                            </tr>
                            <tr>
                                <th>Codigo Cuenta</th>
                                <th>Fecha de Transaccion</th>
                                <th>Hora de Transaccion</th>
                                <th>Tipo de Transaccion</th>
                                <th>Monto</th>
                            </tr>
                        </thead>
                        <tbody>         
                            <%
                                if (list.size() > 0) {
                                    for (Transaccion tr : list) {
                                        t = tr;
                            %>
                            <tr>
                                <td><%=t.getCuenta()%></td>
                                <td><%=t.getFecha()%></td>
                                <td><%=t.getHora()%></td>
                                <td><%=t.getTipo()%></td>
                                <td><%=t.getMonto()%></td>
                            </tr> 
                            <%       }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
