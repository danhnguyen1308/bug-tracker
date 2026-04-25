pipeline {
    agent any

    environment {
        DOCKER_HOST = 'tcp://jenkins-docker:2376'
        DOCKER_CERT_PATH = '/certs/client'
        DOCKER_TLS_VERIFY = '1'
    }

    stages {
        stage('UnitTest Tests - Backend') {
            agent{
                docker {
                    image 'snakee/golang-junit:1.21'
                    reuseNode true
                }
            }
            steps {
                dir('bugtracker-backend') {
                    sh 'go test -v ./...'
                }
            }
        }
    }
}
