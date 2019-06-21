pipeline{
        agent any
        stages{
		stage('---setup---'){
                        steps{
                                sh "sudo rm -rf /var/lib/wildfly-10.1.0.Final/standalone/deployments/*"
                        }
                }
                stage('---clean---'){
                        steps{
				sh "pwd"
				sh "ls"
                                sh "mvn clean -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
                stage('--package--'){
                        steps{
                                sh "mvn package -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
		stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
		stage('--verify--'){
                        steps{
                                sh "mvn verify -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
		stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
				sh "mvn site -f /var/lib/jenkins/workspace/${JOB_NAME}/Java"
                        }
                }
		stage('--deploy--'){
                        steps{
                                sh "cd /"
				sh "pwd"
				sh "sudo cp /var/lib/jenkins/workspace/${JOB_NAME}/Java/target/Yoga.war /var/lib/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
                stage('--email--'){
                        steps{
                                emailext attachLog: true, attachmentsPattern: 'target/site/jacoco/index.html, target/site/surefire-report.html', body: '', subject: '', to: 'ayshamarty@gmail.com'
                        }
                }
        }
}