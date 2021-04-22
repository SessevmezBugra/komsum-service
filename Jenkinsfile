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
                build job: 'pipeline-deploy-app', propagate: true, wait: true
            }
        }

    }
}
