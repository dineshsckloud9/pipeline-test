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
   this.script.stage('Maven build') {
	  	println(this.config.testcase)
    if (this.config.testcase == true) {
        if (this.config.pompath.length() > 0 && this.config.pompath != false) {

            String fileName = this.config.pompath.substring(this.config.pompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 this.tmp = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 this.tmp = "Something else"
            }
	    System.out.println(this.config.pompath.length())
            if (this.tmp != 'xml' && this.config.pompath.charAt(this.config.pompath.length()-1) != File.separatorChar) {
                this.config.pompath += File.separator;
            }

            if (this.tmp == 'xml') {
                return "mvn clean install -DskipTests=true -f " + this.config.pompath
            } else {
                return "mvn clean install -DskipTests=true -f " + this.config.pompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=true"
        }
    } else if (this.config.testcase == false) {
        if (this.config.pompath.length() > 0 && this.config.pompath != false) {

            String fileName = this.config.pompath.substring(this.config.pompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 String extension = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 String extension = "Something else"
            }

            if (extension != 'xml' && this.config.pompath.charAt(this.config.pompath.length()-1) != File.separatorChar) {
                this.config.pompath += File.separator;
            }

            if (extension == 'xml') {
                return "mvn clean install -DskipTests=false -f " + this.config.pompath
            } else {
                return "mvn clean install -DskipTests=false -f " + this.config.pompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=false"
        }
    } else {
        this.script.error "testcase parameter has invalid value!"
	    println(this.config.testcase)
    }
}
	  this.script.sh("echo '***********************ENV Setup Done***********************'")
	  println "Inside Maven"	    
  }
}
