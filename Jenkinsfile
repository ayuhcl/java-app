pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        DOCKER_IMAGE = "lovidovi29/java-app"
        IMAGE_NAME = 'java-poc-app'
    }

    stages {

        
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/ayuhcl/java-app.git'
            }
        }


        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=java-app'
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }


        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
                    docker login -u $USER -p $PASS
                    docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }
}
