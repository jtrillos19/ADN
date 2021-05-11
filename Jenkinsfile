pipeline{
    agent{
        label 'Slave_Mac'
    }

    options{
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK8_Mac' //Preinstalada en la Configuración del Master
    }

    stages{

        stage('Build') {
            steps{
                echo "------------>Build<------------"
                sh './gradlew --b ./build.gradle build -x test'
            }
        }

        stage('Compile & Unit Tests') {
            steps{
                echo "------------>compile & Unit Tests<------------"
                sh 'chmod +x gradlew'
                sh './gradlew --b ./build.gradle test'
            }
        }

        stage('Static Code Analysis') {
            steps{
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
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
            junit 'app/build/test-results/**/*.xml'
        }

    }


}