<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Finalizar Solicitud</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
        %>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 registros">
                    <form method="POST" action="/Project_Banco/ControladorFinalizarAsociacion">
                        <div class="form-group text-center">
                            <h1 class="text-light">Aceptar/Rechazar Asociacion</h1>
                            <img src="../Imagenes/conectar.svg" width="100px"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Codigo Asociacion</label>
                            <input type="text" name="codigoM" value="<%=request.getParameter("codigoA")%> "class="form-control" readonly/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Mi cuenta</label>
                            <input type="text" name="cuentaM" value="<%=request.getParameter("cuentaA")%>" class="form-control" readonly/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Cliente Solicitante</label>
                            <input type="text" name="clienteM" value="<%=request.getParameter("clienteA")%>" class="form-control" readonly/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Aceptar o Rechazar</label>
                            <br>
                            <input type="text" name="estadoM"  list="estados" required="Campo Obligatorio">
                            <datalist id="estados">
                                <option value="ACEPTAR">
                                <option value="RECHAZAR">
                            </datalist>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="form-group mx-sm-4 pt-2">
                            <input type="submit" class="btn btn-block btn-light " value="Finalizar Solicitud"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
