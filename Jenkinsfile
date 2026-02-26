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
                // Ignoramos fallos de tests para que pase al bloque post y envíe el reporte
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
            // El código exacto generado por tu Jenkins con el projectKey inyectado
            step([
                $class: 'XrayImportBuilder',
                serverInstance: 'CLOUD-302a3a46-968c-46bf-b622-b86ce7b9c8d0',
                projectKey: 'LQAE',
                endpointName: '/cucumber',
                importFilePath: 'target/cucumber.json',
                importInParallel: 'false'
            ])
        }
        success {
            echo '✅ Pipeline finalizado con éxito.'
        }
        failure {
            echo '❌ Error crítico en el Pipeline.'
        }
    }
}