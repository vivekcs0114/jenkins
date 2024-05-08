job('demo') {
    steps {
        shell('echo Hello World!')
    }
}

pipelineJob('github-demo') {
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
