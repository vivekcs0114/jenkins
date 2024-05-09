import org.yaml.snakeyaml.Yaml

def projects = ['Microservice']

def createFolder(project, display, repos) {
    folder(project) {
        displayName(project)
        description(display)
    }

    for (repo in repos){
        folder(project + "/" + repo)
    }
}

def createMultiBranchPipeline(project, repo, folder, url) {
    multibranchPipelineJob("$project/$repo/$folder") {
        branchSource {
            source {
                git {
                    repoOwner(project)
                    repository(repo)
                    serverUrl(url)
                }
            }
        }
    }
}

for (projectName in projects) {
    def yaml = new Yaml();
    def file =  readFileFromWorkspace("${projectName}.yaml");
    def fileYaml = yaml.load("${file}");
    def display = fileYaml."$projectName".display
    def repositories = fileYaml."$projectName".repositories
    def repos = repositories.keySet() as List

    createFolder(projectName, display, repos)

    for (item in repositories) {
        def repoName = item.getKey().toString()
        def branchMap = repositories[repoName]['branches']
        def gitUrl = "https://github.com/vivekcs0114"

        for (branch in branchMap) {
            def branchName = branch.getKey()
            def folderMap = repositories[repoName]['branches'][branchName]

            for (folder in folderMap) {
                def folderName = folder.getKey().toString()
                def filePath = repositories[repoName]['branches'][branchName][folderName].filename
                def branches = branchName.replaceAll("[()]", "").replaceAll("|", " ")

                createMultiBranchPipeline(projectName, repoName, folderName, gitUrl)
            }
        }
    }
}