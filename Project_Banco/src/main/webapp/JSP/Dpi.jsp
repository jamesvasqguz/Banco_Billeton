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
        <title>DPI del Cliente</title>
    </head>
    <body>
<%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            String cd = String.valueOf(sesion.getAttribute("cuenta"));
            int codigo = Integer.parseInt(cd);
            DBActualizaciones act = new DBActualizaciones();
            Cliente cl = new Cliente();
            ArrayList<Cliente> listar = act.datosCliente(codigo);
        %>
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
                        <label>DPI PDF</label>
                        <br>
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
