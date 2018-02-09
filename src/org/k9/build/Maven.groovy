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
	  this.script.error "hhhhhhhhhhhhhhhhhhhhhhhh"
	  this.script.sh("echo '$this.config.testcase'")
	  println "Inside Maven"	    
  }
}
