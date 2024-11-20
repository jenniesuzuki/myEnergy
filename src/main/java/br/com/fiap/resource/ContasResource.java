package br.com.fiap.resource;

import br.com.fiap.bo.ContasBO;
import br.com.fiap.to.ContasTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Path("/myenergy/contas")
public class ContasResource {
    private ContasBO contasBO = new ContasBO();
    private static final float LIMITE_CONSUMO = 152.2f;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllContas() {
        ArrayList<ContasTO> resultado = contasBO.findAllContas();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            for (ContasTO conta : resultado) {
                conta.setConsumoExcedente(conta.isConsumoExcedente(LIMITE_CONSUMO));
            }
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findContaById(@PathParam("id") Long id) {
        ContasTO resultado = contasBO.findContaById(id);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            resultado.setConsumoExcedente(resultado.isConsumoExcedente(LIMITE_CONSUMO));
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/cpf/{usuario_cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findContasByCpf(@PathParam("usuario_cpf") String usuarioCpf) {
        ArrayList<ContasTO> contas = contasBO.findContasByCpf(usuarioCpf);
        Response.ResponseBuilder response;

        if (contas != null && !contas.isEmpty()) {
            for (ContasTO conta : contas) {
                conta.setConsumoExcedente(conta.isConsumoExcedente(LIMITE_CONSUMO));
            }
            response = Response.ok(contas);
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity("Nenhuma conta encontrada para o CPF informado.");
        }
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveConta(@Valid ContasTO conta) {
        ContasTO resultado = contasBO.saveConta(conta);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            resultado.setConsumoExcedente(resultado.isConsumoExcedente(LIMITE_CONSUMO));
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConta(@PathParam("id") Long id) {
        Response.ResponseBuilder response = null;
        if (contasBO.deleteConta(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateConta(@Valid ContasTO conta, @PathParam("id") Long id) {
        conta.setId(id);
        ContasTO resultado = contasBO.updateConta(conta);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            resultado.setConsumoExcedente(resultado.isConsumoExcedente(LIMITE_CONSUMO));
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

}
