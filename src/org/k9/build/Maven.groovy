package org.k9.build
import com.cloudbees.groovy.cps.NonCPS
import jenkins.model.Jenkins

class Maven implements Serializable {
  def config
  def script

  Maven(script,config) {
    this.config = config
    this.script = script
  }

  void build() {
    this.script.stage('Build Artifcat') {
      this.script.sh(this.config.buildstep.trim())
    }
  }
}
