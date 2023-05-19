pipeline {
    agent any
    environment {
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh 'npm install --legacy-peer-deps'
            }
        }
        
        stage('Deploy') {
            steps {
                sh './jenkins/scripts/deploy.sh'
            }
        }
    }
}
