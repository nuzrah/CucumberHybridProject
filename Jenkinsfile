pipeline {
    agent any

    stages {
        stage('Extract URL') {
            steps {
                script {
                    def logFilePath = "${WORKSPACE}/log.txt"
                    def logContent = readFile(file: logFilePath)

                    def urlPattern = /(https:\/\/reports\.cucumber\.io\/reports\/[a-f0-9\-]+)/
                    def matcher = (logContent =~ urlPattern)
                    def extractedUrl = matcher ? matcher[0][1] : null

                    env.EXTRACTED_URL = extractedUrl
                }
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn --log-file log.txt' // Replace with your Maven build command
            }
        }

        stage('Send Email') {
            steps {
                emailext (
                    subject: "Jenkins Status | Cucumber Hybrid Project",
                    body: """
                    The extracted URL is:
                    ${env.EXTRACTED_URL}
                    """,
                    to: "nuzrahn@getapplova.com"
                )
            }
        }
    }
}
