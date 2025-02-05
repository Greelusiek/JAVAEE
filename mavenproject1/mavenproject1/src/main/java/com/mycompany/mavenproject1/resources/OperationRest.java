package com.mycompany.mavenproject1.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Path("/")
public class OperationRest {

    //mapa operacji
    private static final Map<String, BiFunction<Double, Double, Double>> operations = new HashMap<>();

    //statyczna inicjalizacja
    static {
        operations.put("add", (x, y) -> x + y);
        operations.put("sub", (x, y) -> x - y);
        operations.put("mul", (x, y) -> x * y);
        operations.put("div", (x, y) -> y != 0 ? x / y : null); // nie dzielimy przez zero
    }

    @GET
    @Path("/{operation}")
    public Response getOperation(
            @PathParam("operation") String operation,
            @QueryParam("x") Double x,
            @QueryParam("y") Double y
    ) {
        if (x == null || y == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nie podano parametrow x i y.")
                    .build();
        }

        if (operation == null || !operations.containsKey(operation)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nie znaleziono operacji.")
                    .build();
        }

        //wykonaj operacje
        BiFunction<Double, Double, Double> func = operations.get(operation);
        Double result = func.apply(x, y);

        if (result == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nierprawidlowa operacja.")
                    .build();
        }

        return Response.ok("Wynik: " + result).build();
    }
}
