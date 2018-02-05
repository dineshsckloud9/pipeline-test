def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

pipeline {
    agent any
    stages {
	stage('Checkout') {
	    steps {
		git url:"${config.gitUrl}"
		}
	}
	stage('Build') {
	    steps {
		sh 'mvn clean install'
	}
      }
        stage ('Copy Artifacts') {
	   steps {
		sh "mkdir -p output"
		sh "cp -vaf target/*.jar output/"
		archiveArtifacts artifacts: 'output/*.jar'
		sh "echo 'ready'"
		sh "echo ${artifacts}"
	}
      }
    }
  }
}
