pipeline{
        agent any
        stages{
                stage('---clean---'){
                        steps{
                                sh "mvn clean"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report"
				sh "mvn site"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp target/movieapp.war /home/matt_joe_hunt/scripts/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}