def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
//	   agent any
	node {
		stage ('Build') {
			if ( "$config.testcase" == "true" ) {
				sh('mvn clean install -DskipTests=true')
				echo "${config.testcase}"
			}
			else if ( "$config.testcase" == "false" ){
				echo "$config.testcase"
				sh('mvn clean install -DskipTests=false')
			}
			else {
				echo "invalid testcase value"
				exit 1
			}
           	}
	}
}
