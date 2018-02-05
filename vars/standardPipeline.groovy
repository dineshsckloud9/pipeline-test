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
            stage ('Build') {
		env.PATH = "${mvnHome}/bin:${env.PATH}",
                sh "mvn clean install"
            }
        } catch (err) {
            currentBuild.result = 'FAILED'
            throw err
        }
    }
}
