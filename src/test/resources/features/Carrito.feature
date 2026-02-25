Feature: Carrito de Compras en SauceDemo

  @agregar-carrito @LQAE-4
  Scenario: Agregar una mochila al carrito exitosamente
    Given que el usuario inicia sesion con "standard_user" y "secret_sauce"
    When agrega la mochila al carrito
    Then el icono del carrito muestra "1" producto