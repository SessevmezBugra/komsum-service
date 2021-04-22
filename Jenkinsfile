pipeline {

    agent any

    stages {

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
