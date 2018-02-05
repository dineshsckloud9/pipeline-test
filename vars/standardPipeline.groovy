def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    pipeline {
	agent any
	
        stages {
            stage ('Checkout') {
                git branch: '${config.Branch}', url: '${config.gitUrl}'
            }
            stage ('Build') {
                sh "mvn clean install"
            }
        }, 
	catch (err) {
            currentBuild.result = 'FAILED'
            throw err
        }
    }
}
