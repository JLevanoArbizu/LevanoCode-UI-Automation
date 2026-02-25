Feature: Autenticación en SauceDemo
  Como usuario del e-commerce
  Quiero iniciar sesión con mis credenciales
  Para poder acceder al catálogo de productos

  @login-exitoso @LQAE-3
  Scenario: Iniciar sesión con credenciales válidas (UI)
    Given que el usuario navega a la página de login de SauceDemo
    When ingresa el nombre de usuario "standard_user" y la contraseña "secret_sauce"
    And hace clic en el botón de iniciar sesión
    Then el sistema redirige al catálogo de productos exitosamente