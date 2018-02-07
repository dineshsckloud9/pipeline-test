package org.k9.build

class Maven implements Serializable {
  def config
  def script
  def tmp

  Maven(script,config) {
    this.config = config
    this.script = script
    this.tmp = false
  }

  void buildmvncommand() {
    if (this.config.mvn_testcase == "true") {
        if (this.config.mvn_pompath.length() > 0 && this.config.mvn_pompath != false) {

            String fileName = this.config.mvn_pompath.substring(this.config.mvn_pompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 this.tmp = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 this.tmp = "Something else"
            }
//	    System.out.println(this.config.mvn_pompath.length())
            if (this.tmp != 'xml' && this.config.mvn_pompath.charAt(this.config.mvn_pompath.length()-1) != File.separatorChar) {
                this.config.mvn_pompath += File.separator;
            }

            if (this.tmp == 'xml') {
                return "mvn clean install -DskipTests=true -f " + this.config.mvn_pompath
            } else {
                return "mvn clean install -DskipTests=true -f " + this.config.mvn_pompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=true"
        }
    } else if (this.config.mvn_testcase == "false") {
        if (this.config.mvn_pompath.length() > 0 && this.config.mvn_pompath != false) {

            String fileName = this.config.mvn_pompath.substring(this.config.mvn_pompath.lastIndexOf("/"))
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                 String extension = fileName.substring(fileName.lastIndexOf(".")+1);
            } else {
                 String extension = "Something else"
            }

            if (extension != 'xml' && this.config.mvn_pompath.charAt(this.config.mvn_pompath.length()-1) != File.separatorChar) {
                this.config.mvn_pompath += File.separator;
            }

            if (extension == 'xml') {
                return "mvn clean install -DskipTests=false -f " + this.config.mvn_pompath
            } else {
                return "mvn clean install -DskipTests=false -f " + this.config.mvn_pompath + "pom.xml"
            }
        } else {
            return "mvn clean install -DskipTests=false"
        }
    } else {
        this.script.error "testcase parameter has invalid value! "
    }
  }
}
