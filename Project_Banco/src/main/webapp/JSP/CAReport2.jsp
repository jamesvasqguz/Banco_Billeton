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
            String rol = String.valueOf(sesion.getAttribute("rol"));
            int codigo = Integer.parseInt(cd);
        %>
        <div class="container mt-5">  
            <div class="row">
                <div class="col-sm">   
                    <table class="table table-bordered table-dark table-hover table-striped">                      
                        <thead class="thead-dark"<>
                            <tr>
                                <th colspan="5">
                                    <h3>Listado de depósitos y retiros realizados durante su turno de manera ordenada, mostrando el total existente en caja (depósitos - retiros).</h3>
                                    <form action="/Project_Banco/ControladorReporteCA2" method="GET">
                                        <input type="date" name="f1" class="form-control" required="Campo Obligatorio"/>
                                        <input type="date" name="f2" class="form-control" required="Campo Obligatorio"/>
                                        <input type="submit" class="btn btn-primary" value="Descargar">
                                    </form>
                                </th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <br>
    </body>
</html>
