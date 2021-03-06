<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.DBConsultas"%>
<%@page import="Backend.Asociacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Solicitudes</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            String cd = String.valueOf(sesion.getAttribute("id"));
            int codigo = Integer.parseInt(cd);
            Asociacion a = new Asociacion();
            DBConsultas cons = new DBConsultas();
            ArrayList<Asociacion> list = cons.asociacionesPendientes(codigo);
            ArrayList<Asociacion> list1 = cons.asociacionesFin(codigo);
        %>
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Cliente.jsp"><%=sesion.getAttribute("username")%></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="InformacionCliente.jsp">Mis Datos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Mis Cuentas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="EstadoCuenta.jsp">Estado de Cuenta</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Asociaciones
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="SolicitarAsociacion.jsp">Solicitar Asociacion</a>
                            <a class="dropdown-item" href="Solicitudes.jsp">Aceptar Asociacion</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Transferencias.jsp">Transferencias</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ReportesCliente.jsp">Reportes</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="CerrarSesion.jsp">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Salir<img src="../Imagenes/out.svg" width="30px"></button>
                </form>
            </div>
        </nav>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js" ></script>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm">    
                    <table class="table table-dark table-bordered table-hover">                      
                        <thead>
                            <tr>
                                <th colspan="5" class="text-center">
                                    <h2>Aceptar o Recharzar Solicitudes</h2>
                                </th>
                            </tr>
                            <tr>
                                <th>Codigo Asociacion</th>
                                <th>Cuenta</th>
                                <th>Cliente Solicitante</th>
                                <th>Estado</th>
                                <th>Aceptar o Rechazar</th>
                            </tr>
                        </thead>
                        <tbody>         
                            <%
                                if (list.size() > 0) {
                                    for (Asociacion as : list) {
                                        a = as;
                            %>
                            <tr>
                                <td><%=a.getCodigo()%></td>
                                <td><%=a.getCuenta()%></td>
                                <td><%=a.getCliente()%></td>
                                <td><%=a.getEstado()%></td>
                                <td><a <a href="FinalizarSolicitud.jsp?codigoA=<%=a.getCodigo()%>&cuentaA=<%=a.getCuenta()%>&clienteA=<%=a.getCliente()%>"><img src="../Imagenes/papel.svg" width="50px"></a></td>
                            </tr> 
                            <%       }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm">    
                    <table class="table table-dark table-bordered table-hover">                      
                        <thead>
                            <tr>
                                <th colspan="4" class="text-center">
                                    <h2>Solicitudes Procesadas</h2>
                                </th>
                            </tr>
                            <tr>
                                <th>Codigo Asociacion</th>
                                <th>Cuenta</th>
                                <th>Cliente Solicitante</th>
                                <th>Estado</th>
                            </tr>
                        </thead>
                        <tbody>         
                            <%
                                if (list1.size() > 0) {
                                    for (Asociacion as : list1) {
                                        a = as;
                            %>
                            <tr>
                                <td><%=a.getCodigo()%></td>
                                <td><%=a.getCuenta()%></td>
                                <td><%=a.getCliente()%></td>
                                <td><%=a.getEstado()%></td>
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
