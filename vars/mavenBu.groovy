def call(body) {

	def config = [:]
	body.resolveStrategy = closure.DELEGATE_FIRST
	body.delegate = config
	body ()

//pipeline {
//	agent any
node
		stage('Checkout') {
				git(gitUrl: config.gitUrl, branch: config.branch)
			}
		stage('Build Artifcat'){
				sh(buildstep: config.buildstep)
			}
//	}
}