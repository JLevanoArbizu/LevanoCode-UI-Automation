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
                // Forzamos que ignore fallos para que el reporte siempre se intente subir
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
            }
        }
    }

    post {
        always {
            echo '📊 Generando Reportes BDD en Jenkins...'
            cucumber buildStatus: 'UNSTABLE',
                     fileIncludePattern: 'target/*.json',
                     sortingMethod: 'ALPHABETICAL'

            echo '☁️ Enviando resultados a Jira (Xray)...'
            // Cambio de sintaxis: xrayImportResults es más estable que step([$class...])
            xrayImportResults (
                serverInstance: 'jira-server',
                projectKey: 'LQAE',
                endpointName: '/cucumber',
                importFilePath: 'target/cucumber.json'
            )
        }
        success {
            echo '✅ Pipeline finalizado con éxito.'
        }
        failure {
            echo '❌ Error crítico en el Pipeline.'
        }
    }
}