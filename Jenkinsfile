pipeline {
    // Ejecutar en cualquier nodo/agente disponible
    agent any

    // Definimos las herramientas globales configuradas en Jenkins
    tools {
        maven 'Maven-3.9' // Asegúrate de que el nombre coincida con tu configuración de Jenkins
        jdk 'JDK-17'      // Asegúrate de que el nombre coincida con tu configuración de Jenkins
    }

    stages {
        stage('📥 Checkout') {
            steps {
                echo 'Descargando código desde GitHub...'
                // Jenkins clona automáticamente la rama configurada en el Job
                checkout scm
            }
        }

        stage('Verificar Entorno') {
            steps {
                sh 'google-chrome --version || echo "Chrome no está instalado"'
                sh 'mvn -version'
            }
        }

        stage('🧪 Ejecución de Pruebas (BDD)') {
            steps {
                echo 'Ejecutando tests con Maven y Cucumber...'
                // Cambiado a 'sh' porque el contenedor de Docker es Linux
                sh 'mvn clean test'
            }
        }
    }

    // El bloque 'post' se ejecuta SIEMPRE, sin importar si la prueba falló o pasó
    post {
        always {
            echo 'Generando Reportes BDD...'
            // Plugin de Cucumber para generar el reporte visual
            cucumber buildStatus: 'UNSTABLE',
                     fileIncludePattern: '**/cucumber.json',
                     sortingMethod: 'ALPHABETICAL'

            // Limpieza del espacio de trabajo (Buena práctica Enterprise)
            cleanWs()
        }
        success {
            echo '✅ Todas las pruebas pasaron exitosamente.'
        }
        failure {
            echo '❌ Algunas pruebas fallaron. Revisa el reporte.'
        }
    }
}