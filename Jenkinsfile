pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    stages {
        stage('📥 Checkout') {
            steps {
                echo 'Descargando código...'
                checkout scm
            }
        }

        stage('🔍 Verificar Entorno') {
            steps {
                echo 'Verificando herramientas instaladas...'
                sh 'java -version'
                sh 'mvn -version'
                sh 'google-chrome --version'
            }
        }

        stage('🧪 Ejecución de Pruebas') {
            steps {
                echo 'Ejecutando tests con Maven...'
                // Importante: Tus Hooks ya tienen la lógica Headless, así que esto funcionará
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Generando Reportes BDD...'
            // Quitamos cleanWs() momentáneamente para asegurar que Cucumber encuentre los JSON
            cucumber buildStatus: 'UNSTABLE',
                      fileIncludePattern: 'target/*.json', // Especificamos la carpeta target
                      sortingMethod: 'ALPHABETICAL'
                      
            echo '☁️ Enviando resultados a Jira (Xray)...'
                        // El alias 'jira-server' es el que configuraste hace un momento
                        step([$class: 'XrayImportBuilder',
                              serverInstance: 'jira-server',
                              endpointName: '/cucumber',
                              projectKey: 'LQAE', // Cambia esto si el "Key" de tu proyecto en Jira es distinto
                              importFilePath: 'target/cucumber.json'
        }
        success {
            echo '✅ Todas las pruebas pasaron exitosamente.'
        }
        failure {
            echo '❌ Error en las pruebas. Revisa el reporte de Cucumber arriba.'
        }
    }
}