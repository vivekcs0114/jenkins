
def listOfParams(parameters) {

}

def fetchParameters() {
    def projectName = 'Microservice'
    
    def yaml = new Yaml();
    def file =  readFileFromWorkspace("${projectName}.yaml");
    def fileYaml = yaml.load("${file}");
    
    def repositories = fileYaml."$projectName".repositories

    def repoName = 'pong'
    def branchMap = repositories[repoName]['branches']

    def branchList = []
    def currentBranch = 'main'
    for (branch in branchMap) {

        if (branch.key.contains(currentBranch)) {
            branchList += branch.key
        }

        def folderList = []
        for (branchItem in branchList) {
            def folderMap = repositories[repoName]['branches'][branchItem]

            for (x in folderMap) {
                folderList += x.key
            }

            for (folder in folderList) {
                if (folder == folderName) {
                    def parameters = repositories[repoName]['branches'][branchItem][folderName].parameters
                    def paramList = []
                    paramList = listOfParams(parameters)
                    return paramList
                }
            }
        }
    }
}

fetchParameters()