Feature: Creación de Cuenta

  Background:
    * url clienteServiceUrl

  Scenario: Crear cuenta para un cliente existente y activo
    # 1. Crear el cliente en cliente-service
    Given path '/clientes'
    And request
      """
      {
        "nombre": "Cliente Cuenta Test",
        "genero": "MASCULINO",
        "edad": 30,
        "identificacion": "1799999999",
        "direccion": "Av Siempre Viva",
        "telefono": "098000000",
        "password": "clave123"
      }
      """
    When method post
    Then status 201
    * def clienteId = response.clienteId

    # 2. cliente-service publica el evento CLIENTE_CREADO de forma
    #    asíncrona; cuenta-service lo consume y lo replica en su propia
    #    base de datos. 'retry until' reintenta este GET hasta que la
    #    réplica exista (o falla tras agotar el timeout configurado en
    #    karate-config.js), en vez de usar un sleep fijo poco confiable.
    Given url cuentaServiceUrl
    And path '/clientes-replica', clienteId
    And retry until responseStatus == 200
    When method get
    Then status 200
    And match response.estado == true

    # 3. Ahora sí, crear la cuenta con la certeza de que la réplica existe
    Given url cuentaServiceUrl
    And path '/cuentas'
    And request
      """
      {
        "clienteId": "#(clienteId)",
        "tipoCuenta": "AHORROS",
        "saldoInicial": 2000
      }
      """
    When method post
    Then status 201
    And match response.numeroCuenta == '#string'
    And match response.estado == true
    And match response.saldoInicial == 2000

  Scenario: Crear cuenta para clienteId inexistente retorna error
    Given url cuentaServiceUrl
    And path '/cuentas'
    And request
      """
      {
        "clienteId": "id-que-no-existe-en-ningun-lado",
        "tipoCuenta": "AHORROS",
        "saldoInicial": 1000
      }
      """
    When method post
    Then status 404