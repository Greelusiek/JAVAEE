package com.mycompany.lab5.resources;

import com.mycompany.lab5.ApplicationBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Path("/")
public class OperationRest {
    
    @Inject
    private ApplicationBean applicationBean;

    //mapa operacji
    private static final Map<String, BiFunction<Double, Double, Double>> operations = new HashMap<>();
    //mapa zamiany
    private static final Map<String, String> switchMap = new HashMap<>();

    //statyczna inicjalizacja
    static {
        operations.put("add", (x, y) -> x + y);
        operations.put("sub", (x, y) -> x - y);
        operations.put("mul", (x, y) -> x * y);
        operations.put("div", (x, y) -> y != 0 ? x / y : null); // nie dzielimy przez zero

        //domyslne wartosci zamiany (brak zamiany)
        switchMap.put("add", "add");
        switchMap.put("sub", "sub");
        switchMap.put("mul", "mul");
        switchMap.put("div", "div");
    }

    @GET
    @Path("/{operation}")
    public Response getOperation(
            @PathParam("operation") String operation,
            @QueryParam("x") Double x,
            @QueryParam("y") Double y
    ) {
        /* usun gwiazdke --> */ //zapis do bazy
        String text = operation + "?x=" + x + "&y=" + y;
        String date = new Date().toString();
        applicationBean.listAdd(text, date);
        /**/

        if (x == null || y == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nie podano parametrow x i y.")
                    .build();
        }

        //zmien operacje
        String mappedOperation = switchMap.get(operation);

        if (mappedOperation == null || !operations.containsKey(mappedOperation)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nie znaleziono operacji.")
                    .build();
        }

        //wykonaj operacje
        BiFunction<Double, Double, Double> func = operations.get(mappedOperation);
        Double result = func.apply(x, y);

        if (result == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nierprawidlowa operacja.")
                    .build();
        }

        return Response.ok("Wynik: " + result).build();
    }

    @GET
    @Path("/switch/{current}/{target}")
    public Response getSwitch(
            @PathParam("current") String current,
            @PathParam("target") String target
    ) {
        /* usun gwiazdke --> */ //zapis do bazy
        String text = "switch/" + current + "/" + "/" + target;
        String date = new Date().toString();
        applicationBean.listAdd(text, date);
        /**/        

        if (!operations.containsKey(target)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Docelowa operacja nie istnieje.")
                    .build();
        }

        if (!operations.containsKey(current)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Aktualna operacja nie istnieje.")
                    .build();
        }

        //zaktualizuj mape zamian
        switchMap.put(current, target);

        return Response.ok("Zmieniono " + current + " na " + target + ".").build();
    }
}
