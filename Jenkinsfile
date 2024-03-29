pipeline {

    agent any

    stages {

        stage('config-server') {
            steps {
                build job: 'config-server-pipeline', propagate: true, wait: true
            }
        }

        stage('eureka-server') {
            steps {
                build job: 'eureka-server-pipeline', propagate: true, wait: true
            }
        }

        stage('geography-service') {
            steps {
                build job: 'geography-service-pipeline', propagate: true, wait: true
            }
        }

        stage('post-service') {
            steps {
                build job: 'post-service-pipeline', propagate: true, wait: true
            }
        }

        stage('tag-service') {
            steps {
                build job: 'tag-service-pipeline', propagate: true, wait: true
            }
        }

        stage('gateway-service') {
            steps {
                build job: 'gateway-service-pipeline', propagate: true, wait: true
            }
        }

        stage('feed-service') {
            steps {
                build job: 'feed-service-pipeline', propagate: true, wait: true
            }
        }

        stage('file-service') {
            steps {
                build job: 'file-service-pipeline', propagate: true, wait: true
            }
        }

        stage('user-service') {
            steps {
                build job: 'user-service-pipeline', propagate: true, wait: true
            }
        }

        stage('deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }

    }
}
