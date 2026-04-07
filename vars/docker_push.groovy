def call(String Project, String ImageTag, String dockerhubuser){
    withCredentials([usernamePassword(
        credentialsId: 'dockerHubCred',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {
        // Use --password-stdin to avoid leaking secrets
        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
    }

    // Push the Docker image
    sh "docker push ${dockerhubuser}/${Project}:${ImageTag}"
}
