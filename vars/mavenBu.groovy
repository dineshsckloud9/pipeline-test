def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
//	   agent any
	node {
		stage ('Build') {
		  def fileType = sh(
			script: "file -z ${config.pomconfpath} | tr -s ' ' | cut -d ' ' -f 2 | tr -dc XML",
			returnStdout: true
			)
			echo "Filetype is: ${fileType}"
		if ( "${fileType}" == "XML" ) {
			if ( "${config.testcase}" == true ) {
						sh("mvn clean install -DskipTests=true -f ${config.pomconfpath}")
						sh("echo ${config.testcase} > /tmp/test")
				}
			else if ( "${config.testcase}" == false ) {
						sh("mvn clean install -DskipTests=false -f ${config.pomconfpath}")
			}
			else {
				echo "invalid testcase value"
				sh("echo ${config.testcase} > /tmp/test")
			}
           	}
		else {
			echo "Some problems were encountered while processing the POMs"
		}
	}
   }
}
