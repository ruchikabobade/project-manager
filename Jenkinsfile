node {
    try {
        def app
        stage('Clone repository') {
            checkout scm
        }
        stage('Tests') {
             sh './gradlew clean test --no-daemon'
         }
        stage('Build Jar') {
            sh 'bash ./build.sh'
        }
        stage('Build Docker image') {
            app = docker.build("ruchikadocker/project-manager-service", "-f ./Dockerfile .")
        }
        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
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
