pipeline {
    agent any

    environment {
        EMAIL_USER = credentials('email-user')
        EMAIL_PASS = credentials('email-pass')
    }

    stages {

        stage('Build (Maven)') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t email-app .'
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                docker run --rm ^
                -e EMAIL_USER=%EMAIL_USER% ^
                -e EMAIL_PASS=%EMAIL_PASS% ^
                email-app
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}
