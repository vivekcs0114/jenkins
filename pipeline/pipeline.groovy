pipeline {
  agent any

  parameters {
    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
  }

  environment {
    // FOO will be available in entire pipeline
    FOO = "PIPELINE"
  }

  stages {
    stage("local") {
      environment {
        // BAR will only be available in this stage
        BAR = "STAGE"
      }
      steps {
        echo "Hello ${params.PERSON}"
        sh 'echo "FOO is $FOO and BAR is $BAR"'
      }
    }
    stage("global") {
      steps {
        sh 'echo "FOO is $FOO and BAR is $BAR"'
      }
    }
  }
}