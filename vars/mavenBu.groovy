def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
//	   agent any
	node {
		stage('test') {
			echo "hello world"
		}
		stage('Checkout') {
			git(Url: config.Url, branch: config.branch)
		}
		stage ('Build') {
			if ( "$config.testcase" == "true" ) {
				sh('mvn clean install -DskipTests=true')
				echo "${config.testcase}"
			}
			else {
				echo "$config.testcase"
				sh('mvn clean install')
			}
		}
	}
}
//			sh("${config.buildstep}")
//			echo "${config.buildstep}"
