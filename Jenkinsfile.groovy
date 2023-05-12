pipeline {
    agent {
        docker {
            image '18-alpine'
            args "-u root -p 3000:3000"
            withCredentials([string(credentialsId: 'anas', variable: 'ROOT_PASSWORD')]) {
                environment {
                    ROOT_PASSWORD = "${env.ROOT_PASSWORD}"
                }
                steps {
                    sh 'echo $ROOT_PASSWORD | sudo -S su'
                }
            }
        }
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
            }
        }
        stage('Test') {
            steps {
                sh './jenkins/scripts/test.sh'
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
                input message: 'Finished using the web site? (Click "Proceed" to continue)'
                sh './jenkins/scripts/kill.sh'
            }
        }
    }
}
