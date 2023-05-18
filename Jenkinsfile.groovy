pipeline {
    agent {
        docker {
            image 'node:18-alpine'
            args '-v $HOME/.npm:/root/.npm' // Montage du volume pour le cache npm
        }
    }
    stages {
        stage('Declarative: Checkout SCM') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps {
                sh 'npm install'
                // Autres étapes de test
            }
        }
        stage('Build') {
            steps {
                // Étapes de construction
            }
        }
        stage('Deliver') {
            steps {
                // Étapes de livraison
            }
        }
    }
}
