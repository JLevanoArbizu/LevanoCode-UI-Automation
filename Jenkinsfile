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
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Generando Reportes BDD...'
            // Genera el reporte visual en Jenkins
            cucumber buildStatus: 'UNSTABLE',
                     fileIncludePattern: 'target/*.json',
                     sortingMethod: 'ALPHABETICAL'

            echo '☁️ Enviando resultados a Jira (Xray)...'

            step([$class: 'XrayImportBuilder',
                  serverInstance: 'jira-server',
                  endpointName: '/cucumber',
                  projectKey: 'LQAE',
                  importFilePath: 'target/cucumber.json'
            ])
        }
        success {
            echo '✅ Todas las pruebas pasaron exitosamente.'
        }
        failure {
            echo '❌ Error en las pruebas. Revisa el reporte de Cucumber arriba.'
        }
    }
}