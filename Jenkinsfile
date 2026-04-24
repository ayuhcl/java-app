pipeline {

    agent any

    environment {

        IMAGE_NAME = 'lovidovi29/app:latest'

    }

    stages {

        stage('Checkout') {

            steps {

                git branch: 'main', url: 'https://github.com/ayuhcl/java-app.git'

            }

        }

        stage('Build') {

            steps {

                sh 'mvn clean install'

            }

        }

        stage('SonarQube Analysis') {

            steps {

                withSonarQubeEnv('sonar-server') {

                    sh 'mvn sonar:sonar'

                }

            }

        }

        stage('Test') {

            steps {

                sh 'mvn test'

            }

        }

        stage('Docker Build') {

            steps {

                sh 'docker build -t $IMAGE_NAME .'

            }

        }

        stage('Docker Push') {

            steps {

                withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {

                    sh 'docker login -u $USER -p $PASS'

                    sh 'docker push $IMAGE_NAME'

                }

            }

        }

    }

    post {

        failure {

            echo "Pipeline failed!"

        }

    }

}
 