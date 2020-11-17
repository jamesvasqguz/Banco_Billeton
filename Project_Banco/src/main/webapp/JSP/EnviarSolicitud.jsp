<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Enviar Solicitud</title>
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
                    <form method="POST" action="/Project_Banco/ControladorSolicitarA">
                        <div class="form-group text-center">
                            <h1 class="text-light">Verificar Datos</h1>
                            <img src="../Imagenes/conectar.svg" width="100px"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Número de Cuenta</label>
                            <input type="text" name="cuenta" class="form-control" value="<%=sesion.getAttribute("cuenta")%>" readonly/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Dueño de la Cuenta</label>
                            <input type="text" name="name" class="form-control" value="<%=sesion.getAttribute("nombre")%>" readonly/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>DPI del Dueño</label>
                            <input type="text" name="dpi" class="form-control" value="<%=sesion.getAttribute("dpi")%>" readonly/>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="alert alert-info" role="alert">
                            <h4>Si los datos son los esperados puede enviar su solicitud, De lo contrario verifique el número de cuenta.</h4>
                        </div>                
                        <div class="dropdown-divider"></div>
                        <div class="alert alert-warning" role="alert">
                            Si ya intento 3 veces solicitar una asociacion con esta cuenta y fueron rechazadas, entonces no se le permitira solicitar una asociacion con esta cuenta nunca más.
                        </div>
                        <div class="form-group mx-sm-4 pt-2">
                            <input type="submit" class="btn btn-block btn-light " value="Enviar Solicitud"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
