def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//	pipeline { 
//	   agent any
	node {
		stage ('Build') {
		   fileType = sh ( 
			file -z "${config.pomconfpath}" | awk '{print $2}'
//			returnStdout: true
			)
			echo "file type is: $fileType"
		   if ($fileType == "XML") {
			if ( "$config.testcase" == true ) {
//                                pompathnum=`echo ${#"${config.pomconfpath}"}`
//                                if ( $pompathnum > 0 && "${config.pomconfpath}" != false ) {
//				fileName=`echo ${"${config.pomconfpath}"##*"/"}`
//					if ( $fileName != "pom.xml" ) {
						sh('mvn clean install -DskipTests=true' -f "${config.pomconfpath}")
//					}
//					else ( $fileName == "pom.xml" ) {
//						echo "Some problems were encountered while processing the POMs"
//					}
				}
			else if ( "$config.testcase" == false ){
						sh('mvn clean install -DskipTests=false' -f "${config.pomconfpath}")
//						echo "${config.testcase}"
			}
//			}
//			else if ( "$config.testcase" == "false" ){
//				echo "$config.testcase"
//				sh('mvn clean install -DskipTests=false')
//			}
			else {
				echo "invalid testcase value"
				exit 1
			}
           	}
		else {
			echo "Some problems were encountered while processing the POMs"
		}
	}
}
