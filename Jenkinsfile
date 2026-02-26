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
                // Ignoramos fallos para que siempre intente subir el reporte a Jira
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
            
            step([
                $class: 'XrayImportBuilder',
                serverInstance: '9e64ae59-c568-4336-a7ea-4fb6a7aa558c',
                projectKey: 'LQAE',
                endpointName: '/cucumber',
                importFilePath: 'target/cucumber.json'
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