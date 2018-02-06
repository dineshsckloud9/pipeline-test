def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
	node {
//	   agent any
		stage('test') {
			echo "hello world"
		}
		stage('Checkout') {
			git(Url: config.Url, branch: config.branch)
		}
		stage ('Build') {
			if ( "${config.testcase}" == true) {
				sh('mvn clean install -DskipTests=true')
//			sh("${config.buildstep}")
//			echo "${config.buildstep}"
			echo "${config.testcase}"
			}
			else {
				sh('mvn clean install')
				echo "${config.testcase}"
			}
		}
	}
}
