def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

	pipeline { 
//	node {
	   agent config.nodes
		stage('test') {
			echo "hello world"
		}
		stage('Checkout') {
			git(Url: config.Url, branch: config.branch)
		}
		stage ('Build') {
			sh("${config.buildstep}")
			echo "${config.buildstep}"
		}
	}

}
