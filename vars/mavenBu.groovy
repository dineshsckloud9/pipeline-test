def call(body) {

	def config = [:]
	body.resolveStrategy = closure.DELEGATE_FIRST
	body.delegate = config
	body ()

//pipeline {
//	agent any
node {
		stage('Checkout') {
				git(Url: config.Url, branch: config.branch)
			}
		stage('Build Artifcat'){
				sh(buildstep: config.buildstep)
			}
	}
}
