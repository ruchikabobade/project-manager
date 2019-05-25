node {
    try {
        def app
        stage('Clone repository') {
            checkout scm
        }
        stage('Tests') {
                    steps {
                        script {
                            try {
                                sh './gradlew clean test --no-daemon'
                            } finally {
                                junit '**/build/test-results/test/*.xml'
                            }
                        }
                    }
         }
        stage('Build Jar') {
            sh 'bash ./build.sh'
        }
        stage('Build Docker image') {
            app = docker.build("ruchikadocker/project-manager-service", "-f ./Dockerfile .")
        }
        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'my-docker-hub') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }
    } finally {
        stage('cleanup') {
            echo "doing some cleanup..."
        }
    }
}