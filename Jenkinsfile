pipeline {
    agent any

    environment {
        // The directory where your built project will be copied
        DEPLOY_DIR = 'C:\\tomcat10\\webapps'
        // Ensure Maven is in your system PATH or defined in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout Source') {
            steps {
                // This pulls the latest code from your GitHub repository
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building Spring Boot application...'
                // 'bat' is for Windows; -DskipTests speeds up the initial pipeline test
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Verify Version') {
            steps {
                // This reads the 'version' file you showed in your screenshot
                script {
                    if (fileExists('version')) {
                        def ver = readFile('version').trim()
                        echo "Building version: ${ver}"
                    }
                }
            }
        }

        stage('Deploy Application') {
            steps {
                echo "Deploying to ${env.DEPLOY_DIR}..."
                bat """
                if not exist "%DEPLOY_DIR%" mkdir "%DEPLOY_DIR%"
                
                :: Copy the compiled .war file from the target folder to your deployment folder
                xcopy /Y "target\\*.war" "%DEPLOY_DIR%"
                
                :: Optional: also copy the version file for tracking
                if exist "version" copy /Y "version" "%DEPLOY_DIR%"
                """
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed! Your WAR file is ready in C:\\deployments\\demos'
        }

        failure {
            echo 'Pipeline failed. Check the Console Output in Jenkins for errors.'
        }
    }
}