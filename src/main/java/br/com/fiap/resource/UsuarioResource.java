package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/myenergy/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsuarios() {
        ArrayList<UsuarioTO> resultado = usuarioBO.findAllUsuarios();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCpf(@PathParam("cpf") String cpf) {
        UsuarioTO resultado = usuarioBO.findByCpf(cpf);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUsuario(@Valid UsuarioTO usuario) {
        UsuarioTO resultado = usuarioBO.saveUsuario(usuario);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{cpf}")
    public Response deleteUsuario(@PathParam("cpf") String cpf) {
        Response.ResponseBuilder response = null;
        if (usuarioBO.deleteUsuario(cpf)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@Valid UsuarioTO usuario, @PathParam("cpf") String cpf) {
        usuario.setCpf(cpf);
        UsuarioTO resultado = usuarioBO.updateUsuario(usuario);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioTO usuario) {
        boolean isAuthenticated = usuarioBO.autenticarUsuario(usuario.getCpf(), usuario.getSenha());
        if (isAuthenticated) {
            return Response.ok(true).build();
        }
        return Response.ok(false).status(400).build();
    }
}
