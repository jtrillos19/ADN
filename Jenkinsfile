pipeline{

   //Donde se va a ejecutar el Pipeline
    agent{
        label 'Slave_Mac'
    }
    //Opciones específicas de Pipeline dentro del Pipeline
    options{
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }
    //Una sección que define las herramientas “preinstaladas” en Jenkins
    tools {
        jdk 'JDK8_Mac' //Preinstalada en la Configuración del Master
    }
    //Aquí comienzan los “items” del Pipeline
    stages{

        stage('Unit Tests') {
            steps{
                echo "------------>compile & Unit Tests<------------"
                sh './gradlew --b build.gradle test --scan'
                sh './gradlew --b build.gradle jacocoTestReport'
            }
        }
        stage('Build') {
            steps{
                echo "------------>Build<------------"
                //Construir sin tarea test que se ejecutó previamente
                sh './gradlew --b build.gradle build -x test'
            }
        }

        stage('Static Code Analysis') {
            steps{
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }
            }
        }
    }

    post {
        failure {
            echo '----------->Fail<------------'
            mail (to: 'jesus.trillos@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
        }

        success {
			echo 'This will run only if successful'
            junit '**/test-results/testDebugUnitTest/*.xml'
        }

    }


}