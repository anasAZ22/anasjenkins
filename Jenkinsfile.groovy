pipeline {
    agent {
        docker {
            image 'node:14-alpine'
            args "-u root -p 3000:3000 -e ROOT_PASSWORD=${env.ROOT_PASSWORD}"
        }
    }
    environment {
        CI = 'true'
        ROOT_PASSWORD = credentials('anas')
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
