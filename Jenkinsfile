pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh """
                    cd /Users/yuwei/Workspace/IDEA/SpringCloudService
                    /Users/yuwei/Softwares/apache-maven-3.5.4/bin/mvn -e -U clean install -Dmaven.test.skip=true
                """
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Transfer') {
            steps {
                echo 'Transfer..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying Start....'
                sh """
                    cd /Users/yuwei/Workspace/IDEA/SpringCloudService
                    /usr/bin/java -jar SpringBootService-0.0.1-SNAPSHOT.jar
                """
                echo 'Deploying End....'
            }
        }
    }

    post {
        always {
            echo 'Run the steps in the post section regardless of the completion status of the Pipeline’s or stage’s run.'
        }

        fixed {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run is successful and the previous run failed or was unstable.'
        }
        regression {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run’s status is failure, unstable, or aborted and the previous run was successful.'
        }
        aborted {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has an "aborted" status, usually due to the Pipeline being manually aborted. This is typically denoted by gray in the web UI.'
        }
        cleanup {
            echo 'Run the steps in this post condition after every other post condition has been evaluated, regardless of the Pipeline or stage’s status.'
        }
        unsuccessful {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has not a "success" status. This is typically denoted in the web UI depending on the status previously mentioned.'
        }

        failure {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has a "failed" status, typically denoted by red in the web UI.'
        }
        success {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has a "success" status, typically denoted by blue or green in the web UI.'
        }
        unstable {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has an "unstable" status, usually caused by test failures, code violations, etc. This is typically denoted by yellow in the web UI.'
        }
        changed {
            echo 'Only run the steps in post if the current Pipeline’s or stage’s run has a different completion status from its previous run.'
        }
    }
}