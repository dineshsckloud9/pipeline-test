package org.k9.build
import org.k9.*

class Maven implements Serializable {
  def config
  def script
  def tmp

  Maven(script,config) {
    this.config = config
    this.script = script
    this.tmp = false
  }

  void build() {
    if (this.config.mvntestcase == "true") {
        if (this.config.mvnpompath.length() > 0 && this.config.mvnpompath != false) {

            String fileName = this.config.mvnpompath.substring(this.config.mvnpompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 this.tmp = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 this.tmp = "Something else"
            }
//	    System.out.println(this.config.mvnpompath.length())
            if (this.tmp != 'xml' && this.config.mvnpompath.charAt(this.config.mvnpompath.length()-1) != File.separatorChar) {
                this.config.mvnpompath += File.separator;
            }

            if (this.tmp == 'xml') {
                return "mvn clean install -DskipTests=true -f " + this.config.mvnpompath
            } else {
                return "mvn clean install -DskipTests=true -f " + this.config.mvnpompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=true"
        }
    } else if (this.config.mvntestcase == "false") {
        if (this.config.mvnpompath.length() > 0 && this.config.mvnpompath != false) {

            String fileName = this.config.mvnpompath.substring(this.config.mvnpompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 String extension = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 String extension = "Something else"
            }

            if (extension != 'xml' && this.config.mvnpompath.charAt(this.config.mvnpompath.length()-1) != File.separatorChar) {
                this.config.mvnpompath += File.separator;
            }

            if (extension == 'xml') {
                return "mvn clean install -DskipTests=false -f " + this.config.mvnpompath
            } else {
                return "mvn clean install -DskipTests=false -f " + this.config.mvnpompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=false"
        }
    } else {
        this.script.error "testcase parameter has invalid value! "
    }
  }
}
