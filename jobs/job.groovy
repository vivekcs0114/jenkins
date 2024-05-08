folder("ABCDE") {
    displayName("ABCDE")
    description('Folder for project ABCDE')
}

job('ABCDE/demo') {
    steps {
        shell('echo Hello World!')
    }
}

pipelineJob('ABCDE/github-demo') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('vivekcs0114/jenkins')
                    }
                }
            }
            scriptPath('pipeline/pipeline.groovy')
        }
    }
}
