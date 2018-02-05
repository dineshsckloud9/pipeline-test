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
		sh 'mvn clean install -DskipTests=true'
	}
      }
        stage ('Copy Artifacts') {
	   steps {
		sh "mkdir -p output"
		sh "cp -vaf target/*.jar output/"
		def artifacts = archiveArtifacts artifacts: 'output/*.jar'
		sh "echo ${artifacts}"
	}
      }
    }
  }
}
