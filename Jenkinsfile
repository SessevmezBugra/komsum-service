pipeline {

    agent any

    stages {

        stage('geography-service') {
            steps {
                build job: 'pipeline-geography-service', propagate: true, wait: true
            }
        }

        stage('deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }

    }
}
