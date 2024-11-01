pipeline {
    agent any

    environment {
        // Define SonarQube server URL and token
        SONAR_HOST_URL = 'http://192.168.50.4:9000'
        SONAR_TOKEN = 'sqp_b0595d326bd46a660a888e61a5b1e1eadcdfbe73'
    }

    stages {
        stage('Git Checkout') {
            steps {
               git branch: 'BoujdariaTijani-5IA-TijaniWalidGroup', url: 'https://github.com/TijaniBouj/5IA-TijaniWalidGroup-projet4springangular.git'
            }
        }

        stage('Maven Clean') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Maven Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Launch Unit Tests') {
            steps {
                sh "mvn test"
            }
        }

        stage('Build and Analyze') {
            steps {
                // Run Maven command with SonarQube analysis
                withSonarQubeEnv('SonarQube') {
                    sh """
                    mvn sonar:sonar \
                        -DskipTests \
                        -Dsonar.projectKey=test1 \
                        -Dsonar.projectName='test1' \
                        -Dsonar.host.url=$SONAR_HOST_URL \
                        -Dsonar.token=$SONAR_TOKEN
                    """
                }
            }
        }

        stage('Run Mockito Tests') {
            steps {
                echo 'Running Mockito Tests'
                sh "mvn -Dtest=StockServiceImplTest test"

            }
        }
    }

    post {
        success {
            slackSend channel: '#devops',
                      color: 'good',
                      message: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' succeeded. Check it here: ${env.BUILD_URL}"
        }
        failure {
            slackSend channel: '#devops',
                      color: 'danger',
                      message: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' failed. Check it here: ${env.BUILD_URL}"
        }
    }
}
