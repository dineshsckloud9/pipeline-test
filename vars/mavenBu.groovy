def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
//	   agent any
	node {
		stage ('Build') {
		  String fileType = sh(
			script: "file -z ${config.pomconfpath} | tr -s ' ' | cut -d ' ' -f 2",
			returnStdout: true
			)
			echo "file itype is: ${fileType}"
		def str = "XML"
		if ("${fileType}" == str) {
			if ( "${config.testcase}" == true ) {
						sh("mvn clean install -DskipTests=true -f ${config.pomconfpath}")
						echo "${config.testcase}"
				}
			else if ( "${config.testcase}" == false ) {
						sh("mvn clean install -DskipTests=false -f ${config.pomconfpath}")
			}
			else {
				echo "invalid testcase value"
			}
           	}
		else {
			echo "Some problems were encountered while processing the POMs"
		}
	}
   }
}
