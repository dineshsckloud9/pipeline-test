def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

pipeline {
    agent any
    tools {
    maven 'maven'
  }
    stages {
        stage('Back-end') {
            steps {
                git 'https://github.com/spring-projects/spring-petclinic.git'
            }
        }
        stage('maven') {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }
    }
  }
}
