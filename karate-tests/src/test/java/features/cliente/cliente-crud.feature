Feature: CRUD de Cliente

  Background:
    * url clienteServiceUrl

  Scenario: Crear un cliente exitosamente
    Given path '/clientes/crear'
    And request
      """
      {
        "nombre": "Jose Lema",
        "genero": "MASCULINO",
        "edad": 35,
        "identificacion": "1755555555",
        "direccion": "Otavalo sn y principal",
        "telefono": "098254785",
        "password": "1234",
        "estado": true
      }
      """
    When method post
    Then status 201
    And match response.clienteId == '#string'
    And match response.estado == true
    And match response.nombre == 'Jose Lema'
    # No debe filtrarse la contraseña en la respuesta
    And match response.password == '#notpresent'

  Scenario: No permitir identificación duplicada
    Given path '/clientes'
    And request
      """
      {
        "nombre": "Marianela Montalvo",
        "genero": "FEMENINO",
        "edad": 28,
        "identificacion": "1766666666",
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965",
        "password": "5678"
      }
      """
    When method post
    Then status 201

    Given path '/clientes'
    And request
      """
      {
        "nombre": "Otro Nombre",
        "genero": "FEMENINO",
        "edad": 30,
        "identificacion": "1766666666",
        "direccion": "Otra direccion",
        "telefono": "099999999",
        "password": "9999"
      }
      """
    When method post
    Then status 409

  Scenario: Buscar cliente por id existente
    Given path '/clientes'
    And request
      """
      {
        "nombre": "Juan Osorio",
        "genero": "MASCULINO",
        "edad": 40,
        "identificacion": "1777777777",
        "direccion": "13 junio y Equinoccial",
        "telefono": "098874587",
        "password": "1245"
      }
      """
    When method post
    Then status 201
    * def clienteCreadoId = response.id

    Given path '/clientes', clienteCreadoId
    When method get
    Then status 200
    And match response.identificacion == '1777777777'

  Scenario: Buscar cliente por id inexistente retorna 404
    Given path '/clientes', 999999
    When method get
    Then status 404

  Scenario: Eliminar cliente es soft delete; deja de ser visible por la API
    Given path '/clientes'
    And request
      """
      {
        "nombre": "Cliente Eliminar",
        "genero": "OTRO",
        "edad": 25,
        "identificacion": "1788888888",
        "direccion": "Calle Falsa 123",
        "telefono": "090000000",
        "password": "abcd"
      }
      """
    When method post
    Then status 201
    * def idEliminar = response.id

    Given path '/clientes', idEliminar
    When method delete
    Then status 204

    # Con estado=false, el cliente ya no se expone vía la API: el registro
    # sigue existiendo en la base de datos (soft delete), pero buscarlo
    # por id ahora responde 404, igual que si no existiera.
    Given path '/clientes', idEliminar
    When method get
    Then status 404

  Scenario: Cliente inactivo no aparece en el listado general
    Given path '/clientes'
    And request
      """
      {
        "nombre": "Cliente Listado Test",
        "genero": "FEMENINO",
        "edad": 33,
        "identificacion": "1711122233",
        "direccion": "Calle Test 456",
        "telefono": "091111111",
        "password": "xyz123"
      }
      """
    When method post
    Then status 201
    * def idListado = response.id

    Given path '/clientes', idListado
    When method delete
    Then status 204

    Given path '/clientes'
    When method get
    Then status 200
    And match response[?(@.id == idListado)] == '#[0]'