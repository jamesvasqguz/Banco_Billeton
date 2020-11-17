<%@page import="java.util.ArrayList"%>
<%@page import="Backend.Cliente"%>
<%@page import="DataBase.DBActualizaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Mi Informacion</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            String cd = String.valueOf(sesion.getAttribute("id"));
            int codigo = Integer.parseInt(cd);
            DBActualizaciones act = new DBActualizaciones();
            Cliente cl = new Cliente();
            ArrayList<Cliente> listar = act.datosCliente(codigo);
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
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 registros">
                    <div class="form-group text-center">
                        <h1 class="text-light">Mi Informacion</h1>
                        <img src="../Imagenes/gerente.svg" width="100px"/>
                    </div>
                    <%           if (listar.size() > 0) {
                            for (Cliente c : listar) {
                                cl = c;
                    %> 
                    <div class="form-group mx-sm-4 pt-3">
                        <label>Codigo Unico</label>
                        <input type="text" name="codigo" value="<%=cl.getCodigo()%> "class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>Nombre</label>
                        <input type="text" name="nombre" value="<%=cl.getNombre()%> "class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>DPI</label>
                        <input type="text" name="dpi" value="<%=cl.getDpi()%>" class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>Fecha Nacimiento</label>
                        <input type="text" name="turno" value="<%=cl.getFecha()%> "class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>Direccion</label>
                        <input type="text" name="direccion" value="<%=cl.getDireccion()%>" class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>Sexo</label>
                        <input type="text" name="sexo" value="<%=cl.getSexo()%>" class="form-control" readonly/>
                    </div>
                    <div class="form-group mx-sm-4 pt-3">
                        <label>DPI PDF</label>
                        <%
                            if (cl.getArchivo() != null) {
                        %>
                        <a href="../ControladorMostrarPDF?id=<%=cl.getCodigo()%>" target="_blank"><img src="../Imagenes/periodico.svg" width="100px"/></a>
                            <%
                                } else {
                                    out.print("SIN ARCHIVO");
                                }
                            %>
                    </div>
                    <%       }
                        }
                    %>
                    <div class="dropdown-divider"></div>
                </div>
            </div>
        </div>  
    </body>
</html>
