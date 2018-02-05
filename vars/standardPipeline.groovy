def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    pipeline {
	agent any

        stages {
            stage('Clone') {
                echo "cloning..."//sh "git clone ${config.gitUrl}"
            }
            //stage ('Build') {
            //    sh "mvn clean install"
            //}
         } 
     }
 }
