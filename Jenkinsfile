pipeline {

    agent any

    stages {

        stage('config-server') {
            steps {
                build job: 'config-server-pipeline', propagate: true, wait: true
            }
        }

        stage('geography-service') {
            steps {
                build job: 'geography-service-pipeline', propagate: true, wait: true
            }
        }

        stage('deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }

    }
}
