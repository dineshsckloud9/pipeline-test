def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    pipeline {
	agent any
        stages {
            stage ('Clone') {
                sh "git clone ${config.gitUrl}"
            }
            //stage ('Build') {
            //    sh "mvn clean install"
            //}
         } 
	}
    }
