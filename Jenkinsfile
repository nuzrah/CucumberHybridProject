pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                checkout scm
            }
        }
        
        stage('Build and Test') {
            steps {
                // Build the Maven project
                sh 'mvn clean test --log-file log.txt' // Replace with your Maven build command
            }
        }
    }
    
    post {
        always {
            // This block will run regardless of the build result
            echo "Post-build action: This always runs"
        }
        
        success {
            // This block will run only if the build is successful
            echo "Post-build action: Build succeeded"
            
            // Extract URL from log file
            def logFilePath = "${WORKSPACE}/log.txt"
            def logContent = readFile(file: logFilePath)
            def urlPattern = /(https:\/\/reports\.cucumber\.io\/reports\/[a-f0-9\-]+)/
            def matcher = (logContent =~ urlPattern)
            def extractedUrl = matcher ? matcher[0][1] : null
            
            // Send email with extracted URL
            emailext (
                subject: "Cucumber Report URL",
                body: """
                The extracted URL is:
                ${extractedUrl}
                """,
                to: "recipient@example.com"
            )
        }
        
        failure {
            // This block will run only if the build fails
            echo "Post-build action: Build failed"
            
            // Extract URL from log file
            def logFilePath = "${WORKSPACE}/log.txt"
            def logContent = readFile(file: logFilePath)
            def urlPattern = /(https:\/\/reports\.cucumber\.io\/reports\/[a-f0-9\-]+)/
            def matcher = (logContent =~ urlPattern)
            def extractedUrl = matcher ? matcher[0][1] : null
            
            // Send email with extracted URL
            emailext (
                subject: "Cucumber Report URL",
                body: """
                The extracted URL is:
                ${extractedUrl}
                """,
                to: "recipient@example.com"
            )
        }
    }
}

