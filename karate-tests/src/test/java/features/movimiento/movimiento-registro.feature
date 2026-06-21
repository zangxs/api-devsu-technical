Feature: Registro de Movimientos

  Background:
    * url cuentaServiceUrl
    # Se asume que existe una cuenta de prueba ya creada con saldoInicial
    # conocido. Ajustar 'cuentaIdPrueba' según el seed de datos de tu
    * def cuentaIdPrueba = 1

  Scenario: Registrar un depósito incrementa el saldo correctamente
    Given path '/movimientos'
    And request
      """
      {
        "cuentaId": "#(cuentaIdPrueba)",
        "valor": 600
      }
      """
    When method post
    Then status 201
    And match response.tipoMovimiento == 'DEPOSITO'
    And match response.valor == 600
    And match response.saldoDisponible == '#number'

  Scenario: Registrar un retiro válido decrementa el saldo
    Given path '/movimientos'
    And request
      """
      {
        "cuentaId": "#(cuentaIdPrueba)",
        "valor": -540
      }
      """
    When method post
    Then status 201
    And match response.tipoMovimiento == 'RETIRO'
    And match response.valor == -540

  Scenario: F3 - Retiro sin saldo suficiente devuelve "Saldo no disponible"
    Given path '/movimientos'
    And request
      """
      {
        "cuentaId": "#(cuentaIdPrueba)",
        "valor": -999999
      }
      """
    When method post
    Then status 400
    And match response.mensaje contains 'Saldo no disponible'

  Scenario: Crear movimiento sobre cuenta inexistente retorna 404
    Given path '/movimientos'
    And request
      """
      {
        "cuentaId": 999999,
        "valor": 100
      }
      """
    When method post
    Then status 404