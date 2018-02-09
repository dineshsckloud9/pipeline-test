package org.k9.build
import org.k9.*

class Maven {
  def config
  def script
  def tmp

  Maven(script,config) {
    this.config = config
    this.script = script
    this.tmp = false
  }

  void build() {
	  this.script.sh("echo '***********************ENV Setup Done***********************'")
	  println "Inside Maven"	    
  }
}
