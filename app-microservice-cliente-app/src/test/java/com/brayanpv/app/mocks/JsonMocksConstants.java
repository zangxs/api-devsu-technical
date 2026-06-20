package com.brayanpv.app.mocks;

public class JsonMocksConstants {

    public static String CREAR_REQUEST = """
            {
                 "nombre": "Stiven Test",
                 "genero": "MASCULINO",
                 "edad": 29,
                 "identificacion": "0234567890",
                 "direccion": "Calle 1 #2-3",
                 "telefono": "3001234567",
                 "password": "1234",
                 "estado": true
               }
            """;

    public static String CREAR_REQUEST_BAD = """
            {
                 "genero": "MASCULINO",
                 "edad": 29,
                 "identificacion": "0234567890",
                 "direccion": "Calle 1 #2-3",
                 "telefono": "3001234567",
                 "password": "1234",
                 "estado": true
               }
            """;

    public static String CREAR_CLIENTE_RESPONSE = """
            {
              "nombre": "Stiven Test",
              "genero": null,
              "edad": 29,
              "identificacion": "0234567890",
              "direccion": "Calle 1 #2-3",
              "telefono": "3001234567",
              "clienteId": 1,
              "estado": true
            }
            """;

    public static String ACTUALIZAR_CLIENTE_REQUEST = """
            {
                   "nombre": "Stiven Palomino",
                    "genero": "MASCULINO",
                    "edad": 29,
                    "identificacion": "0234567890",
                    "direccion": "Calle 1 #2-3",
                    "telefono": 3123825986,
                    "clienteId": 8,
                    "estado": true
                }
            """;

    public static String ACTUALIZAR_CLIENTE_RESPONSE = """
            {
              "nombre": "Stiven Palomino",
              "genero": MASCULINO,
              "edad": 29,
              "identificacion": "0234567890",
              "direccion": "Calle 1 #2-3",
              "telefono": "3123825986",
              "clienteId": 8,
              "estado": true
            }
            """;


}
