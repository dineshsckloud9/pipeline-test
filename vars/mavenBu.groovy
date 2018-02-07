def call(body) {

	def config = [:]
	body.resolveStrategy = closure.DELEGATE_FIRST
	body.delegate = config
	body ()

	node {
		stage('Checkout') {
			git(Url: config.Url, branch: config.branch)
		}
	}
}
