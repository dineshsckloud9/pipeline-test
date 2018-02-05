def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    node {
        // Clean workspace before doing anything
        deleteDir()

        try {
            stage ('Clone') {
                sh "git clone ${config.gitUrl}"
            }
            stage ('Build') {
                sh "/opt/maven/bin/mvn clean install"
            }
        } catch (err) {
            currentBuild.result = 'FAILED'
            throw err
        }
    }
}
